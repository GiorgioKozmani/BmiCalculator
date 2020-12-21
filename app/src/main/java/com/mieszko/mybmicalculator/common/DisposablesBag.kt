package com.mieszko.mybmicalculator.common

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.internal.disposables.DisposableContainer

/**
 * Disposables bag - an abstraction of CompositeDisposable
 * The purpose if this class is to make compositeDisposableTestable
 *
 * @constructor Create empty Disposables bag
 */
interface DisposablesBag : Disposable, DisposableContainer {
    fun clear()
    fun size(): Int
}

class DisposablesBagImpl(private val compositeDisposable: CompositeDisposable) : DisposablesBag {
    override fun clear() = compositeDisposable.clear()

    override fun dispose() = compositeDisposable.dispose()

    override fun add(disposable: Disposable) = compositeDisposable.add(disposable)

    override fun remove(disposable: Disposable) = compositeDisposable.remove(disposable)

    override fun delete(disposable: Disposable) = compositeDisposable.delete(disposable)

    override fun size(): Int = compositeDisposable.size()

    override fun isDisposed(): Boolean = compositeDisposable.isDisposed
}