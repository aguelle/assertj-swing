/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.swing.driver;

import static org.assertj.swing.core.TestRobots.singletonRobotMock;
import static org.mockito.Mockito.mock;

import javax.swing.JList;

import org.junit.BeforeClass;

/**
 * Base test case for {@link JListDriver} that uses mocks as part of its fixture.
 * 
 * @author Alex Ruiz
 */
public class JListDriver_withMocks_TestCase {
  static JList list;
  static JListDriver driver;

  @BeforeClass
  public static void setUpOnce() {
    list = mock(JList.class);
    driver = new JListDriver(singletonRobotMock());
  }
}
