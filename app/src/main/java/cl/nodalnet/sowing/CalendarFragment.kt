package cl.nodalnet.sowing

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import cl.nodalnet.sowing.model.viewmodel.MyViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import kotlinx.android.synthetic.main.block_sup_inter.*
import kotlinx.android.synthetic.main.fragment_calendar.*
import kotlinx.android.synthetic.main.fragment_month.*
import kotlinx.android.synthetic.main.menuarroz.*

class CalendarFragment : Fragment() {

    lateinit var mViewModel: MyViewModel
    var mMonth: String? =null
    var mTitle: String? =null
    val mBundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        arguments?.let {
            mMonth =it.getString("month","")
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
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mUrlImg = mViewModel.getTitleImgFromMonth(mMonth.toString())
        val mMeow: MeowBottomNavigation = meowArrozBottom

        mMeow.add(MeowBottomNavigation.Model(1,R.drawable.ic_home))
        mMeow.add(MeowBottomNavigation.Model(2,R.drawable.ic_calendar))
        mMeow.add(MeowBottomNavigation.Model(3,R.drawable.ic_rrss))
        mMeow.add(MeowBottomNavigation.Model(4,R.drawable.ic_setting))

        mMeow.show(2)

        mMeow.setOnClickMenuListener {
            val name = when (it.id) {
                1 -> findNavController().navigate(R.id.action_calendarFragment_to_FirstFragment)
                2 -> ""
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
        Glide.with(this)
            .load(mUrlImg)
            .transform(CenterCrop(), RoundedCorners(80))
            .into(imgBackgroundSupInter)

        tvAll.setOnClickListener{
            mMonth = "all"
            mTitle = "Listado Completo"
            gotoMonth(mMonth!!, mTitle!!)
         }

        tvEne.setOnClickListener{
            mMonth = "ene"
            mTitle = "Enero"
            gotoMonth(mMonth!!, mTitle!!)
        }

        tvFeb.setOnClickListener{
            mMonth = "feb"
            mTitle = "Febrero"
            gotoMonth(mMonth!!, mTitle!!)
        }
        tvMar.setOnClickListener{
            mMonth = "mar"
            mTitle = "Marzo"
            gotoMonth(mMonth!!, mTitle!!)
        }
        tvAbr.setOnClickListener{
            mMonth = "abr"
            mTitle = "Abril"
            gotoMonth(mMonth!!, mTitle!!)
        }
        tvMay.setOnClickListener{
            mMonth = "may"
            mTitle = "Mayo"
            gotoMonth(mMonth!!, mTitle!!)
        }
        tvJun.setOnClickListener{
            mMonth = "jun"
            mTitle = "Junio"
            gotoMonth(mMonth!!, mTitle!!)
        }
        tvJul.setOnClickListener{
            mMonth = "jul"
            mTitle = "Julio"
            gotoMonth(mMonth!!, mTitle!!)
        }
        tvAgo.setOnClickListener{
            mMonth = "ago"
            mTitle = "Agosto"
            gotoMonth(mMonth!!, mTitle!!)
        }
        tvSep.setOnClickListener{
            mMonth = "sep"
            mTitle = "Septiembre"
            gotoMonth(mMonth!!, mTitle!!)
        }
        tvOct.setOnClickListener{
            mMonth = "oct"
            mTitle = "Octubre"
            gotoMonth(mMonth!!, mTitle!!)
        }
        tvNov.setOnClickListener{
            mMonth = "nov"
            mTitle = "Noviembre"
            gotoMonth(mMonth!!, mTitle!!)
        }
        tvDic.setOnClickListener{
            mMonth = "dic"
            mTitle = "Diciembre"
            gotoMonth(mMonth!!, mTitle!!)
        }


    }

    fun gotoMonth(mMonth:String, mTitle:String){
        mBundle.putString("month",mMonth)
        mBundle.putString("title",mTitle)
        findNavController().navigate(R.id.action_calendarFragment_to_monthFragment,mBundle)
    }



}