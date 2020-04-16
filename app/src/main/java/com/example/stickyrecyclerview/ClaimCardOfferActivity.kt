package com.example.stickyrecyclerview

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Fade
import androidx.transition.TransitionManager
import com.example.stickyrecyclerview.adapter.OfferAdapter
import kotlinx.android.synthetic.main.activity_claim_card_offer.*

class ClaimCardOfferActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_claim_card_offer)
        rv_offers.apply {
            layoutManager = LinearLayoutManager(
                this@ClaimCardOfferActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = OfferAdapter()
        }

        btn_claim.setOnClickListener {
            /* val transition = Slide(Gravity.TOP)
             transition.duration = 1000
             transition.addTarget(R.id.cv_claim_offer)
             transition.addListener(object : Transition.TransitionListener {
                 override fun onTransitionEnd(transition: Transition) {
                     cv_claim_offer.post {
                         if (cv_claim_offer.visibility == View.VISIBLE) {
                             cv_claim_offer.visibility = View.GONE
                             btn_claim.visibility = View.GONE
                         } else {
                             cv_claim_offer.visibility = View.VISIBLE
                             btn_claim.visibility = View.VISIBLE
                         }
                     }
                 }

                 override fun onTransitionResume(transition: Transition) {
                 }

                 override fun onTransitionPause(transition: Transition) {
                 }

                 override fun onTransitionCancel(transition: Transition) {
                 }

                 override fun onTransitionStart(transition: Transition) {
                 }

             })
             TransitionManager.beginDelayedTransition(fl_claim_offer, transition)
 */
            // Claim button animation start
            val transition = Fade()
            transition.duration = 200
            transition.addTarget(R.id.btn_claim)
            TransitionManager.beginDelayedTransition(fl_claim_offer, transition)
            btn_claim.visibility = View.GONE
            // Claim button animation end

            // Text animation from bottom to center start
            val animator = ValueAnimator.ofFloat(cv_claim_offer.height.toFloat(), 0f)
            animator.addUpdateListener {
                cv_claim_offer.layoutParams.height = (it.animatedValue as Float).toInt()
                if ((it.animatedValue as Float).toInt() == 0) {
                    cv_claim_offer.visibility = View.GONE
                    group_offer_claimed.visibility = View.VISIBLE
                    iv_confetti_dots.visibility = View.VISIBLE
                    iv_top_down_dotted_grid.visibility = View.VISIBLE
                    setOtherAnimation()
                } else {
                    cv_claim_offer.requestLayout()
                }
            }
            animator.duration = 500
            animator.start()
            // Text animation from bottom to center end

        }
    }

    private fun setOtherAnimation() {
        val upToDownAnimation =
            AnimationUtils.loadAnimation(this, R.anim.anim_translate_top_to_down)
        tv_claimed_offer_emoji.startAnimation(upToDownAnimation)
        tv_you_have_claimed.startAnimation(upToDownAnimation)
        val upToDownAnimation1 =
            AnimationUtils.loadAnimation(this, R.anim.anim_translate_top_to_down)
        upToDownAnimation1.duration = 400
        iv_confetti_dots.startAnimation(upToDownAnimation1)

        val bottomToUpAnimation =
            AnimationUtils.loadAnimation(this, R.anim.anim_translate_bottom_to_up)
        tv_offer_add_to_account.startAnimation(bottomToUpAnimation)


        /*val transition = Slide(Gravity.TOP)
        transition.duration = 500
        transition.addTarget(R.id.tv_claimed_offer_emoji)
        TransitionManager.beginDelayedTransition(fl_claim_offer, transition)*/

    }
}
