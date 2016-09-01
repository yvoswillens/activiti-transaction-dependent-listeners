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
package org.activiti.demo.listener;

import org.activiti.bpmn.model.FlowElement;
import org.activiti.engine.delegate.TransactionDependentExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Yvo Swillens
 */
@Component("myMessageProducer")
public class MyMessageProducer implements TransactionDependentExecutionListener {

    private static final Logger logger = LoggerFactory.getLogger(MyMessageProducer.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    public void notify(String processInstanceId, String executionId, FlowElement currentFlowElement,
                       Map<String, Object> executionVariables, Map<String, Object> customPropertiesMap) {

        logger.debug("Sending message <{}> to queue", executionId);
        jmsTemplate.convertAndSend("receive_task_signal", executionId);
    }
}
