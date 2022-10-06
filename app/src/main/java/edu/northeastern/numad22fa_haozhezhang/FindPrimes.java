package edu.northeastern.numad22fa_haozhezhang;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

public class FindPrimes extends AppCompatActivity {
    protected Future<String> future;
    protected final String TAG = "FindPrimes: ";
    boolean isRunning = false;
    TextView primeTextView;
    TextView isPrimeDisplay;



    private AlertDialog terminateDialog;
    private AlertDialog goBackDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_primes);
        primeTextView = findViewById(R.id.prime_text_view);
        isPrimeDisplay = findViewById(R.id.is_prime_display);
    }

    /**
     * using executor service to perform asynchronous task
     */
     // using executor service
//    public void findPrimes (View view) {
//        isRunning = true;
//        ExecutorService executor = Executors.newSingleThreadExecutor();
////        ExecutorService executor = Executors.newFixedThreadPool(1);
//        executor.submit(new Runnable() {
//            @Override
//            public void run() {
//                int i = 3;
//                int counter = 1;
////                Log.d(TAG, "enter loop at " + i);
//                    while (isRunning) {
//                        counter ++;
//                        i += 2;
//                        // using counter to avoid displaying all checked numbers
//                        if (counter % 3 == 0 | counter % 5 == 0) {
////                            Log.d(TAG, "counter: " + counter);
//                            try {
//                                if (isPrime(i)) {
////                                    primeTextView.setText(String.valueOf(i));
//                                    primeTextView.setText("Current number being checked: " + i);
//                                    isPrimeDisplay.setText("Prime.");
//                                } else {
////                                    primeTextView.setText(String.valueOf(i));
//                                    primeTextView.setText("Current number being checked: " + i);
//                                    isPrimeDisplay.setText("Not prime.");
//                                }
//                                Thread.sleep(1000);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//
////                Snackbar.make(view, "The last displayed prime is: ", Snackbar.LENGTH_LONG).show();
//            }
//        });
//
//        executor.shutdown();
//    }
    private boolean isPrime(int num) {
        for (int i = 2; i <= num / i; ++i) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public void terminateSearch(View view) {
        if (!isRunning) {
            Toast.makeText(getApplicationContext(),"Already Stopped", Toast.LENGTH_LONG)
                    .show();
            return;
        }
        createTerminateSearchDialog();
        terminateDialog.show();
    }

    public void createTerminateSearchDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.activity_dialog_find_primes, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(view);

        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("Yes",
                        (dialog, action) -> {
                            isRunning = false;
                        })
                .setNegativeButton("No",
                        (dialog, action) -> dialog.cancel());
        terminateDialog = alertDialogBuilder.create();
    }

//    // status Check
//    public void statusCheck(View view) {
//        TextView textView = findViewById(R.id.status_check_text_view);
//
//        if (future.isDone()) {
//            String result = null;
//            try {
//                result = future.get(3, TimeUnit.SECONDS);
//            } catch (ExecutionException | InterruptedException | TimeoutException e) {
//                e.printStackTrace();
//            }
//            textView.setText(result);
//        } else {
//            textView.setText("Process Running......");
//        }
//    }

    /**
     * Using runnable
     *
     * */
    public void findPrimes(View view) {
        isRunning = true;
        PrimeRunnable newPrime = new PrimeRunnable();
        new Thread(newPrime).start();

    }


    class PrimeRunnable implements Runnable {
        private static final String TAG = "MainActivity";
        private Handler textHandler = new Handler();
        //thread.runing /live
        @Override
        public void run() {
            for (int i = 3; ; i += 2) {
                if (!isRunning) break;
                final int finalI = i;
                textHandler.post(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                        primeTextView.setText("Currently number being checked: " + finalI);
                        if (isPrime(finalI)) {
                            isPrimeDisplay.setText("Yes, it's a Prime.");
                        } else {
                            isPrimeDisplay.setText("No, it's not a Prime.");
                        }
                    }
                });
                Log.d(TAG, "Running on a different thread using Runnable Interface: " + i);
                try {
                    Thread.sleep(1000); //Makes the thread sleep or be inactive for 1 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void createGoBackDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.activity_dialog_go_back, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(view);


        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("Yes",
                        (dialog, action) -> {
                    isRunning = false;
                    onBackPressed();
                        })
                .setNegativeButton("No",
                        (dialog, action) -> dialog.cancel());

        goBackDialog = alertDialogBuilder.create();

    }

    @Override
    public void onBackPressed() {

        if (isRunning) {
            Log.d(TAG, "program running, check if wanna quit");
            createGoBackDialog();
            goBackDialog.show();
        } else {
            super.onBackPressed();
        }
    }
}



