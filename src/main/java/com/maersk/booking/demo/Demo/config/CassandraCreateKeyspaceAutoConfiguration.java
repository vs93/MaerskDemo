//package com.maersk.booking.demo.Demo.config;
//
//import com.datastax.oss.driver.api.core.CqlIdentifier;
//import com.datastax.oss.driver.api.core.CqlSession;
//import com.datastax.oss.driver.api.core.CqlSessionBuilder;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.autoconfigure.AutoConfigureBefore;
//import org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration;
//import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.data.cassandra.core.cql.generator.CreateKeyspaceCqlGenerator;
//import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
//
///**
// * create the configured keyspace before the first cqlSession is instantiated. This is guaranteed by running this
// * autoconfiguration before the spring-boot one.
// */
//@ConditionalOnClass(CqlSession.class)
//@ConditionalOnProperty(name = "spring.data.cassandra.create-keyspace", havingValue = "true")
//@AutoConfigureBefore(CassandraAutoConfiguration.class)
//public class CassandraCreateKeyspaceAutoConfiguration {
//
//    private static final Logger logger = LoggerFactory.getLogger(CassandraCreateKeyspaceAutoConfiguration.class);
//
//    public CassandraCreateKeyspaceAutoConfiguration(CqlSessionBuilder cqlSessionBuilder, CassandraProperties properties) {
//        // It's OK to mutate cqlSessionBuilder because it has prototype scope.
//        try (CqlSession session = cqlSessionBuilder.withKeyspace((CqlIdentifier) null).build()) {
//            logger.info("Creating keyspace {} ...", properties.getKeyspaceName());
//            session.execute(CreateKeyspaceCqlGenerator.toCql(
//                    CreateKeyspaceSpecification.createKeyspace(properties.getKeyspaceName()).ifNotExists()));
//        }
//    }
//}
