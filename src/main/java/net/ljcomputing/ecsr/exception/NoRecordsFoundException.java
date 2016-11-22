/**
           Copyright 2016, James G. Willmore

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package net.ljcomputing.ecsr.exception;

/**
 * No Records Found Exception.
 *
 * @author James G. Willmore
 */
public class NoRecordsFoundException extends Exception {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -3619157200467813095L;

  /**
   * Instantiates a new no records found exception.
   */
  public NoRecordsFoundException() {
    super();
  }

  /**
   * Instantiates a new no records found exception.
   *
   * @param message the message
   * @param cause the cause
   * @param enableSuppression the enable suppression
   * @param writable the writable stack trace
   */
  public NoRecordsFoundException(final String message, final Throwable cause,
      final boolean enableSuppression, final boolean writable) {
    super(message, cause, enableSuppression, writable);
  }

  /**
   * Instantiates a new no records found exception.
   *
   * @param message the message
   * @param cause the cause
   */
  public NoRecordsFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }

  /**
   * Instantiates a new no records found exception.
   *
   * @param message the message
   */
  public NoRecordsFoundException(final String message) {
    super(message);
  }

  /**
   * Instantiates a new no records found exception.
   *
   * @param cause the cause
   */
  public NoRecordsFoundException(final Throwable cause) {
    super(cause);
  }
}
