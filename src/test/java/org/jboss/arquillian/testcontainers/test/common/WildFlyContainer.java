/*
 * Copyright The Arquillian Authors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.jboss.arquillian.testcontainers.test.common;

import org.jboss.arquillian.testcontainers.api.LoggingConsumer;
import org.jboss.arquillian.testcontainers.test.QualifiedInjectionTest;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

/**
 * @author <a href="mailto:jperkins@redhat.com">James R. Perkins</a>
 */
@WildFly
public class WildFlyContainer extends GenericContainer<WildFlyContainer> {
    public WildFlyContainer() {
        super(DockerImageName.parse("quay.io/wildfly/wildfly:latest-jdk11"));
    }

    @SuppressWarnings("resource")
    @Override
    protected void configure() {
        withLogConsumer(LoggingConsumer.of(QualifiedInjectionTest.class));
        waitingFor(Wait.forListeningPorts(8080));
        waitingFor(Wait.forLogMessage(".*WFLYSRV0025.*", 1));
    }
}