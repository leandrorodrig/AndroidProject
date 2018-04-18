// Generated code from Butter Knife. Do not modify!
package leandrorodrig.com.br.desafioandroid.repository;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import leandrorodrig.com.br.desafioandroid.R;

public class ReposAdapter$NetworkStateItemViewHolder_ViewBinding implements Unbinder {
  private ReposAdapter.NetworkStateItemViewHolder target;

  @UiThread
  public ReposAdapter$NetworkStateItemViewHolder_ViewBinding(ReposAdapter.NetworkStateItemViewHolder target,
      View source) {
    this.target = target;

    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progress_bar, "field 'progressBar'", ProgressBar.class);
    target.errorMsg = Utils.findRequiredViewAsType(source, R.id.error_msg, "field 'errorMsg'", TextView.class);
    target.button = Utils.findRequiredViewAsType(source, R.id.retry_button, "field 'button'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ReposAdapter.NetworkStateItemViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.progressBar = null;
    target.errorMsg = null;
    target.button = null;
  }
}
