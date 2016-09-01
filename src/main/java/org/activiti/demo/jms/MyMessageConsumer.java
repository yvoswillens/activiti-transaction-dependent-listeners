/**
 * Activiti app component part of the Activiti project
 * Copyright 2005-2015 Alfresco Software, Ltd. All rights reserved.
 * <p>
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * <p>
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.activiti.demo.jms;

import org.activiti.engine.RuntimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author Yvo Swillens
 */
@Component
public class MyMessageConsumer {

    private static final Logger logger = LoggerFactory.getLogger(MyMessageConsumer.class);

    @Autowired
    private RuntimeService runtimeService;

    @JmsListener(destination = "receive_task_signal", containerFactory = "myFactory")
    public void receiveMessage(String executionId) {
        logger.debug("Received message: <" + executionId + ">");
        logger.debug("Signaling execution with id: <" + executionId + ">");

        runtimeService.trigger(executionId);
    }
}
