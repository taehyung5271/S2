package khs.mjc.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BucketFrame#newInstance} factory method to
 * create an instance of this fragment.
 */
public interface DialogFragmentListener {
    void onDataPass(String data);

}