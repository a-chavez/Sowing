package cl.nodalnet.sowing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import cl.nodalnet.sowing.model.viewmodel.MyViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kotlinx.android.synthetic.main.fragment_home.*


class Home : Fragment() {

    lateinit var mViewModel:MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mFullMonth:String = mViewModel.getCurrentMonth()[1]
        val mActualMonth:String = mViewModel.getCurrentMonth()[0]
        val mBundle = Bundle()

        Glide.with(imgTitleHome.context)
            .load("https://www.gardentech.com/-/media/images/gardentech-na/us/blog/starting-seeds-right-in-your-garden/starting_seeds_right_in_your_garden_header.jpg")
            .transform(CenterCrop(),RoundedCorners(50))
            .into(imgTitleHome)
        tvMonthHome.setText(mFullMonth)

        layoutActual.setOnClickListener{
            mBundle.putString("month",mActualMonth)
            mBundle.putString("title",mFullMonth)
            findNavController().navigate(R.id.action_FirstFragment_to_monthFragment,mBundle)
        }

        layoutCalendar.setOnClickListener{
            mBundle.putString("month","all")
            mBundle.putString("title","Listado Completo")
            findNavController().navigate(R.id.action_FirstFragment_to_monthFragment,mBundle)
        }
    }



}