package libraryapp.kchen52.com.surreypubliclibraryclone.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import libraryapp.kchen52.com.surreypubliclibraryclone.R

// Only used during app startup cause apparently we need to define a class for the fragment in the
// main activity xml
class BlankFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }
}