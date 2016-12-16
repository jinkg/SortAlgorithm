/**
 * 34921
 * 2016/12/16.
 */
public class ThreadLocalTest {
    private static ThreadLocal<TestModel> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(runnable).start();
        }
    }

    static class TestModel {
        int id;
    }

    private static Runnable runnable = () -> {
        TestModel mode = createModel((int) Thread.currentThread().getId());
        threadLocal.set(mode);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        schedule();
    };

    private static TestModel createModel(int initId) {
        TestModel testModel = new TestModel();
        testModel.id = initId;
        return testModel;
    }

    private static void schedule() {
        printModel(threadLocal.get());
    }

    private static void printModel(TestModel model) {
        System.out.println("current Thread id: " + Thread.currentThread().getId() + " model id: " + model.id
                + " model hash code: " + model.hashCode());
    }
}
