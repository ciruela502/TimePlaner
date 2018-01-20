package martakonik.timeplaner.interactors

interface MainActivityToWatchPresenterInteractor {
    fun onActivityCreate()
    fun onStop()
    fun onStartWorkingButtonClick()
    fun onFinishWorkingButtonClick()
    fun onDetailsButtonClick()
}