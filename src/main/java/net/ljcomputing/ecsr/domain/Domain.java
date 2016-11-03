package net.ljcomputing.ecsr.domain;

import java.util.Date;

/**
 * Interface shared by all domain classes.
 */
public interface Domain extends Identifier {

  /**
   * Gets the delete.
   *
   * @return the delete
   */
  Boolean getDelete();

  /**
   * Sets the delete.
   *
   * @param delete the new delete
   */
  void setDelete(Boolean delete);

  /**
   * Gets the created timestamp.
   *
   * @return the created timestamp
   */
  Date getCreatedTs();

  /**
   * Sets the created timestamp.
   *
   * @param createdTs the new created timestamp
   */
  void setCreatedTs(Date createdTs);

  /**
   * Gets the modified timestamp.
   *
   * @return the modified timestamp
   */
  Date getModifiedTs();

  /**
   * Sets the modified timestamp.
   *
   * @param modifiedTs the new modified timestamp
   */
  void setModifiedTs(Date modifiedTs);

  /**
   * Called prior to persisting the domain.
   */
  void create();

  /**
   * Indicate that given domain can be deleted from the persisted data source.
   */
  void delete();

  /**
   * Indicate that given domain can be undeleted from the persisted data source.
   */
  void undelete();

  /**
   * Checks if the given domain can be deleted from the persisted data source.
   *
   * @return the boolean
   */
  Boolean isDeleted();

  /**
   * Called prior to modifying the persisted domain.
   */
  void modified();

}