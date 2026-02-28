package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int attempt = 0;
    private static final int MAX_RETRY_COUNT = 2;

    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess()) {
            if (attempt < MAX_RETRY_COUNT) {
                attempt++;
                System.out.println("Retrying test: " + result.getName() + " | Attempt: " + (attempt + 1));
                return true; // retry
            }
        }
        return false; // no more retries
    }
}