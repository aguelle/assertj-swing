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
package org.assertj.swing.junit.ant;

import static java.lang.System.lineSeparator;
import static org.apache.tools.ant.util.FileUtils.close;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.util.DOMElementWriter;
import org.assertj.swing.junit.xml.XmlNode;

/**
 * Understands writing the contents of an XML document to an <code>{@link OutputStream}</code>.
 *
 * @author Alex Ruiz
 */
class XmlOutputWriter {

  private static final String CHARSET = "UTF8";
  private static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";
  private static final String INDENT = "  ";

  private final StandardOutputStreams outputStreams;

  XmlOutputWriter() {
    this(new StandardOutputStreams());
  }

  XmlOutputWriter(StandardOutputStreams outputStreams) {
    this.outputStreams = outputStreams;
  }

  void write(XmlNode xml, OutputStream out) {
    write(xml, out, new DOMElementWriter());
  }

  void write(XmlNode xml, OutputStream out, DOMElementWriter xmlWriter) {
    Writer writer = null;
    try {
      writer = new BufferedWriter(new OutputStreamWriter(out, CHARSET));
      writer.write(XML_HEADER);
      writer.write(lineSeparator());
      xmlWriter.write(xml.target(), writer, 0, INDENT);
      writer.flush();
    } catch (IOException ex) {
      throw new BuildException("Unable to write log file", ex);
    } finally {
      if (!outputStreams.isStandardOutOrErr(out))
        close(writer);
    }
  }
}
