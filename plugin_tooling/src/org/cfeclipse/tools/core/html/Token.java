/*
 * Copyright (c) 2012, the Dart project authors.
 * 
 * Licensed under the Eclipse Public License v1.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.cfeclipse.tools.core.html;

/**
 * A value and location tuple.
 * 
 * @coverage dart.tools.core
 */
public class Token {
  private String value;
  private int location;
  private int line = -1;
  private boolean whitespace;

  public Token(String value, int location) {
    this.value = value;
    this.location = location;
  }

  public Token(String value, int location, int line) {
    this.value = value;
    this.location = location;
    this.line = line;
  }

  public int getLineNumber() {
    return line;
  }

  public int getLocation() {
    return location;
  }

  public String getValue() {
    return value;
  }

  public boolean isWhitespace() {
    return whitespace;
  }

  @Override
  public String toString() {
    return "[" + value + "]";
  }

  void setWhitespace(boolean value) {
    whitespace = value;
  }

}
