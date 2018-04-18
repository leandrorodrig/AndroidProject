// Generated code from Butter Knife. Do not modify!
package leandrorodrig.com.br.desafioandroid;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RepositoryActivity_ViewBinding implements Unbinder {
  private RepositoryActivity target;

  @UiThread
  public RepositoryActivity_ViewBinding(RepositoryActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RepositoryActivity_ViewBinding(RepositoryActivity target, View source) {
    this.target = target;

    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.lstRepo, "field 'recyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RepositoryActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerView = null;
  }
}
