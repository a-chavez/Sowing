package cl.nodalnet.sowing
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentManager.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import cl.nodalnet.sowing.model.viewmodel.MyViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import kotlinx.android.synthetic.main.block_superior.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.menuarroz.*

class Home : Fragment() {

    lateinit var mViewModel:MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.down)
        exitTransition = inflater.inflateTransition(R.transition.fade)

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
        val mMeow: MeowBottomNavigation = meowArrozBottom

        mMeow.add(MeowBottomNavigation.Model(1, R.drawable.ic_home))
        mMeow.add(MeowBottomNavigation.Model(2, R.drawable.ic_calendar))
        mMeow.add(MeowBottomNavigation.Model(3, R.drawable.ic_rrss))
        mMeow.add(MeowBottomNavigation.Model(4, R.drawable.ic_setting))

        mMeow.show(1)

        mMeow.setOnClickMenuListener {
            val name = when (it.id) {
                1 -> ""
                2 -> findNavController().navigate(R.id.action_FirstFragment_to_calendarFragment)
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

        Glide.with(imgBackgroundSup.context)
            .load("https://www.gardentech.com/-/media/images/gardentech-na/us/blog/starting-seeds-right-in-your-garden/starting_seeds_right_in_your_garden_header.jpg")
            .transform(CenterCrop(), RoundedCorners(100))
            .into(imgBackgroundSup)

        //tvMonthHome.setText(mFullMonth)

        layoutActual.setOnClickListener{
            mBundle.putString("month", mActualMonth)
            mBundle.putString("title", mFullMonth)
            findNavController().navigate(R.id.action_FirstFragment_to_monthFragment, mBundle)
        }

        layoutCalendar.setOnClickListener{
            mBundle.putString("month", mActualMonth)
            findNavController().navigate(R.id.action_FirstFragment_to_calendarFragment, mBundle)
        }
        layoutIdea.setOnClickListener{
            mBundle.putString("month", mActualMonth)
            mBundle.putString("title", mFullMonth)
            findNavController().navigate(R.id.action_FirstFragment_to_tipsFragment,mBundle)
        }
    }



}