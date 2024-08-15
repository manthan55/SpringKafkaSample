# SpringRedisSample
This is a sample application demonstrating Kafka messaging in Spring Boot. This app is also deployable to AWS ElasticBeanstalk and integrates with AWS MSK.

---

## Reach out to Me
- [LinkedIn - Manthan Patidar](https://www.linkedin.com/in/manthan-patidar/)
- [Slack - Manthan Patidar](https://scaler-co.slack.com/team/U0375PUUKFC)
    - Reachable only by Scaler students


## Application Overview

- This repo contains two Spring Boot Projects
    - KafkaProducer
    - KafkaConsumer
- Both projects have a hello world endpoint at `/` (which redirects to `/actuator/info`)
- In KafkaProducer project, there is a send message endpoint `/kafka/sendMessage`
- Refer postman folder for collection & environment (you can import in postman)

## AWS Setup
- AWS MSK setup is quite tricky and riddled with options and configurations - which makes it harder to document here -- feel free to reach out to me and I'll be happy to walk you through setting up AWS MSK

## References

- https://aws.amazon.com/blogs/developer/building-an-apache-kafka-data-processing-java-application-using-the-aws-cdk/
- https://github.com/aws-samples/amazon-msk-java-app-cdk/blob/main/consumer/src/main/resources/application.yaml
- https://stackoverflow.com/questions/78406291/invalid-value-software-amazon-msk-auth-iam-iamclientcallbackhandler
- https://github.com/aws/aws-msk-iam-auth/issues/96
- https://github.com/aws/aws-msk-iam-auth
- https://medium.datadriveninvestor.com/aws-msk-public-access-a-comprehensive-tutorial-c40fa001fd58
- https://www.inveniolsi.com/blog/spring-boot-elastic-beanstalk-with-amazon-msk
- https://docs.aws.amazon.com/msk/latest/developerguide/getting-started.html
- https://stackoverflow.com/a/76776430
- https://stackoverflow.com/questions/77236774/how-to-connect-aws-msk-from-spring-boot-or-from-local-system-with-iam-based-auth
- https://stackoverflow.com/questions/73897992/spring-kafka-consumer-factory-for-multiple-model-classes
- https://stackoverflow.com/questions/74989178/aws-msk-configuration-issue-with-spring

For sending JSON messages via Kafka

- https://www.geeksforgeeks.org/spring-boot-pass-json-object-into-kafka-topic/
- https://www.geeksforgeeks.org/spring-boot-consume-json-object-from-kafka-topics/
- https://stackoverflow.com/questions/51688924/spring-kafka-the-class-is-not-in-the-trusted-packages