package martakonik.timeplaner.shared

import io.reactivex.disposables.Disposable
import io.reactivex.internal.disposables.DisposableContainer

fun Disposable.disposeWith(container: DisposableContainer) {
    container.add(this)
}