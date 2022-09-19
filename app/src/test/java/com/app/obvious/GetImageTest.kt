package com.app.obvious

import com.app.obvious.model.Image
import com.app.obvious.ui.data.ImageDataSource
import com.app.obvious.ui.data.LocalDataSource
import com.app.obvious.ui.data.ImageRepository
import com.app.obvious.ui.usecases.GetImages
import com.app.obvious.utils.AppExecutorsInterface
import com.app.obvious.utils.TestSchedulerProvider
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.schedulers.TestScheduler
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class GetImageTest {
    @Mock
    lateinit var imageDataSource: ImageDataSource

    @Mock
    lateinit var localDataSource: LocalDataSource
    private lateinit var imageRepository: ImageRepository
    private lateinit var getImages: GetImages
    private lateinit var appExecutorsInterface: AppExecutorsInterface
    private val testScheduler = TestScheduler()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        appExecutorsInterface = TestSchedulerProvider(testScheduler)
        getImages = GetImages(
            imageDataSource,
            appExecutorsInterface
        )

        imageRepository = ImageRepository(localDataSource)
    }

    @Test
    fun `buildUseCaseSingle should invoke imageDataSource activate call`() {
        `when`(
            imageDataSource.getAllImages()
        ).thenReturn(Maybe.just(listOf(Image("Sample title", "Sample url"))))

        val testObserver = getImages.buildUseCaseSingle(
            null
        ).test()

        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)
        testObserver.assertComplete()
        assertTrue(testObserver.values().last().get(0).getTitle() == listOf(Image("Sample title", "Sample url")).get(0).getTitle())
    }

    @Test
    fun `imageRepository should invoke localDataSource activate call`() {
        `when`(
            localDataSource.fetchData()
        ).thenReturn(Maybe.just(listOf(Image("Sample title", "Sample url"))))

        val testObserver = imageRepository.getAllImages().test()

        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)
        testObserver.assertComplete()
        assertTrue(testObserver.values().last().get(0).getTitle() === listOf(Image("Sample title", "Sample url")).get(0).getTitle())
    }
}
