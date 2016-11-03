package net.ljcomputing.ecsr.repository;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ PersonCreationUnitTests.class, EmailCreationUnitTests.class,
    MailCreationUnitTests.class, PhoneCreationUnitTests.class, CiCreationUnitTests.class })
public class AllTests {

}
