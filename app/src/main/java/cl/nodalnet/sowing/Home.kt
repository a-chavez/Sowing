package cl.nodalnet.sowing

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cl.nodalnet.sowing.model.viewmodel.MyAdapter
import cl.nodalnet.sowing.model.viewmodel.MyViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_home.*

class Home : Fragment(), MyAdapter.SeedNameTxt  {

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

        val mRecyclerView = recyclerView2
        val mAdapter = MyAdapter(this)

        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(context)

        Glide.with(this)
            .load("https://www.gardentech.com/-/media/images/gardentech-na/us/blog/starting-seeds-right-in-your-garden/starting_seeds_right_in_your_garden_header.jpg")
            .into(imgTitleHome)
        mViewModel.exposeLiveDataFromServer().observe(viewLifecycleOwner, Observer {
            Log.d("Arroz", it.toString())

            mAdapter.updateListSeed(it)
        })
    }

    override fun passData(mSeedNameTxt: String) {
            val mBundle = Bundle()
            mBundle.putString("seedName", mSeedNameTxt)
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,mBundle)
    }


}