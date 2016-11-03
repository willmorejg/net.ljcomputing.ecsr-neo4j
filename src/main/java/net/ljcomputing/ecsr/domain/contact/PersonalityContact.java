package net.ljcomputing.ecsr.domain.contact;

import net.ljcomputing.ecsr.domain.Domain;
import net.ljcomputing.ecsr.domain.person.Personality;

/**
 * Abstract implementation of a personality contact 
 * (ex. an email address associated with a person).
 *
 * @author James G. Willmore
 * @param <T> the personality that owns the contact (ex. person)
 * @param <S> the contact information (ex. email address)
 */
public interface PersonalityContact<T extends Personality, S extends ContactInformation>
    extends Domain {

  /**
   * Gets the personality.
   *
   * @return the personality
   */
  T getPersonality();

  /**
   * Sets the personality.
   *
   * @param personality the new personality
   */
  void setPersonality(T personality);

  /**
   * Gets the contact information.
   *
   * @return the contact information
   */
  S getContact();

  /**
   * Sets the contact information.
   *
   * @param contact the new contact
   */
  void setContact(S contact);

}