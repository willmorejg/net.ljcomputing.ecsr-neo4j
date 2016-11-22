/**
           Copyright 2015, James G. Willmore

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

package net.ljcomputing.ecsr.controller;

import org.springframework.http.HttpStatus;

/**
 * Error information.
 * 
 * @author James G. Willmore
 */
public class ErrorInfo {

  /** The timestamp. */
  private final String timestamp;

  /** The status. */
  private final String status;

  /** The error. */
  private final String error;

  /** The message. */
  private final String message;

  /** The path. */
  private final String path;

  /**
   * Instantiates a new error info.
   *
   * @param timestamp the timestamp
   * @param status the status
   * @param path the path
   * @param exception the exception
   */
  public ErrorInfo(final String timestamp, final HttpStatus status, final String path,
      final Exception exception) {
    final int statusCode = status.value();
    final Integer statusCodeObj = Integer.valueOf(statusCode);
    final String localizedMessage = exception.getLocalizedMessage();
    final String message = exception.getMessage();

    this.timestamp = timestamp;
    this.status = statusCodeObj.toString();
    this.error = status.getReasonPhrase();
    this.path = path;

    if (localizedMessage != null) {
      this.message = localizedMessage;
    } else if (message != null) {
      this.message = message;
    } else {
      this.message = "An error occured during processing: " + exception.toString();
    }
  }

  /**
   * Gets the timestamp.
   *
   * @return the timestamp
   */
  public String getTimestamp() {
    return timestamp;
  }

  /**
   * Gets the status.
   *
   * @return the status
   */
  public String getStatus() {
    return status;
  }

  /**
   * Gets the error.
   *
   * @return the error
   */
  public String getError() {
    return error;
  }

  /**
   * Gets the message.
   *
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * Gets the path.
   *
   * @return the path
   */
  public String getPath() {
    return path;
  }
}
