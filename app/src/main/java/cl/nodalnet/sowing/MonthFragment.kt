package cl.nodalnet.sowing

import android.content.Intent
import android.net.Uri
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
import androidx.transition.TransitionInflater
import cl.nodalnet.sowing.model.room.SowingItem
import cl.nodalnet.sowing.model.viewmodel.MyAdapter
import cl.nodalnet.sowing.model.viewmodel.MyViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import kotlinx.android.synthetic.main.block_sup_inter.*
import kotlinx.android.synthetic.main.fragment_month.*
import kotlinx.android.synthetic.main.menuarroz.*

class MonthFragment : Fragment(), MyAdapter.SeedNameTxt {
    lateinit var mViewModel: MyViewModel
    var mMonth: String? =null
    var mTitle: String? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        arguments?.let {
            mMonth =it.getString("month","")
            mTitle =it.getString("title","")
        }

        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.down)
        exitTransition = inflater.inflateTransition(R.transition.fade)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_month, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mRecyclerView = recyclerView2
        val mAdapter = MyAdapter(this)
        var mGet:List<SowingItem> = emptyList()
        val mUrlImg = mViewModel.getTitleImgFromMonth(mMonth.toString())
        val mMeow: MeowBottomNavigation = meowArrozBottom

        mMeow.add(MeowBottomNavigation.Model(1,R.drawable.ic_home))
        mMeow.add(MeowBottomNavigation.Model(2,R.drawable.ic_calendar))
        mMeow.add(MeowBottomNavigation.Model(3,R.drawable.ic_rrss))
        mMeow.add(MeowBottomNavigation.Model(4,R.drawable.ic_setting))

        mMeow.setOnClickMenuListener {
            val name = when (it.id) {
                1 -> findNavController().navigate(R.id.action_monthFragment_to_FirstFragment)
                2 -> findNavController().navigate(R.id.action_monthFragment_to_calendarFragment)
                3 -> startActivity(
                    Intent(
                        Intent.ACTION_SENDTO,
                        Uri.parse("mailto:antonio@nodalnet.cl?subject=Feedback Sowing!")
                    )
                )
                4 -> ""
                else ->""
            }

        }

        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(context)

        Glide.with(this)
            .load(mUrlImg)
            .transform(CenterCrop(), RoundedCorners(80))
            .into(imgBackgroundSupInter)
        tvTitleInter2.setText(mTitle)

        mViewModel.exposeLiveDataFromServer() .observe(viewLifecycleOwner, Observer {
            if (mMonth=="all") {
                mGet = it
            } else{
                mGet = mViewModel.getSeedFromMonth(mMonth.toString(), it)
            }
            mAdapter.updateListSeed(mGet)
            Log.d("Arroz ListSize",mGet.size.toString())
        })

    }


    override fun passData(mSeedNameTxt: String) {
        val mBundle = Bundle()
        mBundle.putString("seedName", mSeedNameTxt)
        mBundle.putString("month",mMonth)
        mBundle.putString("title",mTitle)
       findNavController().navigate(R.id.action_monthFragment_to_SecondFragment, mBundle)
    }

}