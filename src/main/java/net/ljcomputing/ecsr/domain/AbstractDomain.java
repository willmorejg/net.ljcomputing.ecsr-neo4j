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

import java.util.Date;

/**
 * Abstract implementation of a domain.
 * 
 * @author James G. Willmore
 *
 */
public abstract class AbstractDomain extends AbstractIdentifier implements Domain {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 5279563303710662760L;

  /** The delete indicator. */
  private Boolean deleteIndicator; //NOPMD

  /** The created ts. */
  private Date createdTs;

  /** The modified ts. */
  private Date modifiedTs;

  /**
   * @see net.ljcomputing.ecsr.domain.Domain#getDelete()
   */
  @Override
  public Boolean getDelete() {
    return deleteIndicator;
  }

  /**
   * @see net.ljcomputing.ecsr.domain.Domain#setDelete(java.lang.Boolean)
   */
  @Override
  public void setDelete(final Boolean delete) {
    this.deleteIndicator = delete;
  }

  /**
   * @see net.ljcomputing.ecsr.domain.Domain#getCreatedTs()
   */
  @Override
  public Date getCreatedTs() {
    return createdTs;
  }

  /**
   * @see net.ljcomputing.ecsr.domain.Domain#setCreatedTs(java.util.Date)
   */
  @Override
  public void setCreatedTs(final Date createdTs) {
    this.createdTs = createdTs;
  }

  /**
   * @see net.ljcomputing.ecsr.domain.Domain#getModifiedTs()
   */
  @Override
  public Date getModifiedTs() {
    return modifiedTs;
  }

  /**
   * @see net.ljcomputing.ecsr.domain.Domain#setModifiedTs(java.util.Date)
   */
  @Override
  public void setModifiedTs(final Date modifiedTs) {
    this.modifiedTs = modifiedTs;
  }

  /**
   * @see net.ljcomputing.ecsr.domain.Domain#create()
   */
  @Override
  public void create() {
    super.create();

    deleteIndicator = false;
    final Date now = new Date();

    setCreatedTs(now);
    setModifiedTs(now);
  }

  /**
   * @see net.ljcomputing.ecsr.domain.Domain#delete()
   */
  @Override
  public void delete() {
    deleteIndicator = true;
    setModifiedTs(new Date());
  }

  /**
   * @see net.ljcomputing.ecsr.domain.Domain#undelete()
   */
  @Override
  public void undelete() {
    deleteIndicator = false;
    setModifiedTs(new Date());
  }

  /**
   * @see net.ljcomputing.ecsr.domain.Domain#isDeleted()
   */
  @Override
  public Boolean isDeleted() {
    return deleteIndicator;
  }

  /**
   * @see net.ljcomputing.ecsr.domain.Domain#modified()
   */
  @Override
  public void modified() {
    setModifiedTs(new Date());
  }
}
