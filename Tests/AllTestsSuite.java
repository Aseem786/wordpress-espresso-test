package Tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/*+----------------------------------------------------------------------
 ||
 ||  Class AllTestsSuite
 ||
 ||         Author:  Aseem Tiwari
 ||
 ||         Purpose: This class is used to execute all the test classes from one place.
 ++-----------------------------------------------------------------------*/
@RunWith(Suite.class)
@Suite.SuiteClasses({
        SignInTest.class,
        EditSiteTitleTest.class,
        AddBlogPostTest.class,
        EditBlogPostTest.class,
        LikeBlogPostTest.class,
        CommentBlogPostTest.class,
        DeleteBlogPostTest.class})
public class AllTestsSuite {

}
