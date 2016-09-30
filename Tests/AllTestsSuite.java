package Tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs all instrumentation tests from one place
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SignInTest.class,
        AddBlogPostTest.class,
        EditBlogPostTest.class,
        SearchAndCommentBlogPostTest.class,
        DeleteBlogPostTest.class})
public class AllTestsSuite {

}
