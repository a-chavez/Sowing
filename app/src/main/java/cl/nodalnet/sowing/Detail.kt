package cl.nodalnet.sowing

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import cl.nodalnet.sowing.model.viewmodel.MyViewModel
import com.bumptech.glide.Glide
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.menuarroz.*

class Detail : Fragment() {

    lateinit var mViewModel: MyViewModel
    var mSeedNameTxt: String? =null
    var mMonth: String? =null
    var mTitle: String? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        arguments?.let {
            mSeedNameTxt = it.getString("seedName","")
            mMonth = it.getString("month","")
            mTitle = it.getString("title","")
        }
        //animation
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.down)
        exitTransition = inflater.inflateTransition(R.transition.top)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mMeow: MeowBottomNavigation = meowArrozBottom

        mMeow.add(MeowBottomNavigation.Model(1,R.drawable.ic_home))
        mMeow.add(MeowBottomNavigation.Model(2,R.drawable.ic_calendar))
        mMeow.add(MeowBottomNavigation.Model(3,R.drawable.ic_rrss))
        mMeow.add(MeowBottomNavigation.Model(4,R.drawable.ic_setting))

        mMeow.setOnClickMenuListener {
            val name = when (it.id) {
                1 -> findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment2)
                2 -> findNavController().navigate(R.id.action_SecondFragment_to_calendarFragment)
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
        mSeedNameTxt?.let {
            mViewModel.getOneSeed(it).observe(viewLifecycleOwner, Observer {
                tvSeedName.setText(it.name.capitalize())
                Glide.with(this).load(it.urlImage).into(imgDetail)

                tvTemp.setText(it.temp.toString())
                tvSun.setText(it.sun.toString().capitalize())
                tvWather.setText(it.wather.toString().capitalize())
                tvWeather.setText(it.weather.toString().capitalize())
                tvSubstratum.setText(it.substratum.toString().capitalize())
                tvDepth.setText(it.depth.toString().capitalize())
                tvFertilizer.setText(it.fertilizer.toString().capitalize())
                tvDistance.setText(it.distance.toString().capitalize())
                tvPot.setText(it.pot.toString().capitalize())
                tvFriends.setText(it.friends.toString().capitalize())
                tvNonFriends.setText(it.nonfriends.toString().capitalize())
                tvDiseases.setText(it.diseases.toString().capitalize())
            })
        }
    }
}