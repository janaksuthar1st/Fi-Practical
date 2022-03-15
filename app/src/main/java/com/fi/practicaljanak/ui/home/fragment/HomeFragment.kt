package com.fi.practicaljanak.ui.home.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.TextView
import com.fi.practicaljanak.R
import com.fi.practicaljanak.base.BaseFragment
import com.fi.practicaljanak.databinding.FragmentHomeBinding
import com.fi.practicaljanak.extensions.*
import com.fi.practicaljanak.utils.CommonUtils
import com.fi.practicaljanak.utils.FI_URL
import com.fi.practicaljanak.utils.LEARN_MORE
import com.fi.practicaljanak.viewmodels.HomeScreenViewModel
import kotlin.reflect.KClass


class HomeFragment : BaseFragment<HomeScreenViewModel, FragmentHomeBinding>(),
    View.OnClickListener {

    override val modelClass: KClass<HomeScreenViewModel>
        get() = HomeScreenViewModel::class

    override fun getViewBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun isActivityScopeViewModel(): Boolean = true

    override fun initViews(view: View) {
        val builder = CommonUtils.getBankSetupDescSpannableStringBuilder(
            requireContext(),
            resources.getString(R.string.desc_setup_bank),
            resources.getString(R.string.lbl_learn_more)
        ) {
            if (it == LEARN_MORE) {
                CommonUtils.openBrowser(requireContext(), FI_URL)
            }
        }

        binding.tvBankSetupDesc.movementMethod = LinkMovementMethod.getInstance()
        binding.tvBankSetupDesc.setText(builder, TextView.BufferType.SPANNABLE)
    }

    override fun initSetup() {
        binding.btnNext.disable()
    }

    override fun listeners() {
        binding.etPan.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().isEmpty() || s.toString().checkIsValidPanCard()) {
                    binding.etPan.error = null
                } else {
                    binding.etPan.error = getString(R.string.error_invalid_pan_number)
                }
                allFieldValidation()
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        binding.etDD.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().isEmpty() || s.toString().checkIsValidDay()) {
                    binding.etDD.error = null
                } else {
                    binding.etDD.error = getString(R.string.error_invalid_day)
                }
                allFieldValidation()
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        binding.etMM.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().isEmpty() || s.toString().checkIsValidMonth()) {
                    binding.etMM.error = null
                } else {
                    binding.etMM.error = getString(R.string.error_invalid_month)
                }
                allFieldValidation()
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        binding.etYYYY.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().isEmpty() || s.toString().checkIsValidYear()) {
                    binding.etYYYY.error = null
                } else {
                    binding.etYYYY.error = getString(R.string.error_invalid_year)
                }
                allFieldValidation()
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        binding.tvIDontHavePan.setOnClickListener(this)
        binding.btnNext.setOnClickListener(this)
    }

    private fun allFieldValidation(){
        if (binding.etPan.text.toString().checkIsValidPanCard() &&
            binding.etDD.text.toString().checkIsValidDay() &&
            binding.etMM.text.toString().checkIsValidMonth() &&
            binding.etYYYY.text.toString().checkIsValidYear()){
            binding.btnNext.enable()
        }else{
            binding.btnNext.disable()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment().apply {
            arguments = Bundle().apply {
                //No bundle here to use
            }
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.tvIDontHavePan -> {
                requireActivity().finish()
            }

            binding.btnNext -> {
                showToast(requireContext(), getString(R.string.msg_details_submitted_successfully))
                requireActivity().finish()
            }
        }
    }
}