package mactech.fitplanner;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application>{
    TaskDao dao;

    public ApplicationTest() {
        super(Application.class);
    }

    public void ShouldReturnCount(){
        dao = new TaskDao();
        int taskCount = dao.getTaskCount();
        System.out.print(taskCount);

    }
}