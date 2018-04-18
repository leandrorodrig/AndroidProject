// Generated code from Butter Knife. Do not modify!
package leandrorodrig.com.br.desafioandroid.repository;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import leandrorodrig.com.br.desafioandroid.R;

public class ReposAdapter$ReposItemViewHolder_ViewBinding implements Unbinder {
  private ReposAdapter.ReposItemViewHolder target;

  @UiThread
  public ReposAdapter$ReposItemViewHolder_ViewBinding(ReposAdapter.ReposItemViewHolder target,
      View source) {
    this.target = target;

    target.txtNomeRepo = Utils.findRequiredViewAsType(source, R.id.txtNomeRepo, "field 'txtNomeRepo'", TextView.class);
    target.txtDescRepo = Utils.findRequiredViewAsType(source, R.id.txtDescRepo, "field 'txtDescRepo'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ReposAdapter.ReposItemViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.txtNomeRepo = null;
    target.txtDescRepo = null;
  }
}
