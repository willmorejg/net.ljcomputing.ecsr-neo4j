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

package net.ljcomputing.ecsr.domain;

import java.util.UUID;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * @author James G. Willmore
 *
 */
@NodeEntity
public abstract class AbstractIdentifier implements Identifier {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -42427374887036190L;

  /** The id. */
  @GraphId
  private Long id;

  /** The uuid. */
  private String uuid;

  /**
   * @see net.ljcomputing.ecsr.domain.Identifier#getId()
   */
  @Override
  public Long getId() {
    return id;
  }

  /**
   * @see net.ljcomputing.ecsr.domain.Identifier#setId(java.lang.Long)
   */
  @Override
  public void setId(final Long id) {
    this.id = id;
  }

  /**
   * @see net.ljcomputing.ecsr.domain.Identifier#getUuid()
   */
  @Override
  public String getUuid() {
    return uuid;
  }

  /**
   * Sets the uuid.
   *
   * @param uuid the new uuid
   */
  public void setUuid(final String uuid) {
    this.uuid = uuid;
  }

  /**
   * @see net.ljcomputing.ecsr.domain.Identifier#create()
   */
  @Override
  public void create() {
    uuid = UUID.randomUUID().toString(); //NOPMD
  }
}
