package com.geneticthought.audit;

import com.geneticthought.audit.model.User;
import com.geneticthought.audit.repository.UserRepository;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class RegisteredUserEventEndToEndTest {

    @ClassRule
    public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, "users");

    @Autowired
    private UserRepository userRepository;

    private Map<String, Object> senderProps = KafkaTestUtils.producerProps(embeddedKafka);
    private ProducerFactory<Integer, String> producerFactory = new DefaultKafkaProducerFactory<>(senderProps);
    private KafkaTemplate<Integer, String> template = new KafkaTemplate<>(producerFactory);

    @Test
    public void savesEventToRepository() {
        String login = "StephenKing";
        String data = registeredUserEvent(login, "2018-06-19T23:56:25.060501");
        template.send("users", data);

        await().atMost(10, SECONDS).until(() -> userRepository.count() == 1);

        User user = userRepository.findByLogin(login);
        assertThat(user.getId()).isNotNull();
        assertThat(user.getCreationTime()).isNotNull();
    }

    private String registeredUserEvent(String login, String creationTime) {
        return "{" +
                    "\"login\":\"" + login + "\"," +
                    "\"creationTime\":\"" + creationTime + "\"" +
                "}";
    }

}
