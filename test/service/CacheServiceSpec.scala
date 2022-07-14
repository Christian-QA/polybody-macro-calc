package service

import connectors.PolybodyConnector
import dto.MacroCalcDto
import helpers.{ActivityLevel, Male, MaleOrFemale, VeryActive}
import org.mockito.Mockito.{reset, when}
import org.scalatest.BeforeAndAfterEach
import play.api.cache.AsyncCacheApi
import services.{CacheService, MacroStatService}
import utils.BaseSpec

import java.time.LocalDate
import scala.concurrent.duration.{Duration, SECONDS}
import scala.concurrent.{Await, Future}

class CacheServiceSpec extends BaseSpec with BeforeAndAfterEach {

  lazy val mockCache: AsyncCacheApi = mock[AsyncCacheApi]

  lazy val sut = new CacheService(mockCache)

  override def beforeEach(): Unit = {
    super.beforeEach()
    reset(mockCache)
  }

  "CacheService" when {
    "cacheToWeightDouble is called" must {
      "return weight double if it exists in the cache" in {
        when(mockCache.get[Double]("currentWeight"))
          .thenReturn(Future.successful(Some(150.5d)))

        sut.cacheToWeightDouble mustBe Some(150.5d)
      }
      "return None if it does not exist in the cache" in {
        when(mockCache.get[Double]("currentWeight"))
          .thenReturn(Future.successful(None))

        sut.cacheToWeightDouble mustBe None
      }

    }

    "cacheToShortDto is called" must {
      "return a MacroCalcDto with Options set as None if all required values exist" in {
        val macroCalcDto: MacroCalcDto = new MacroCalcDto(
          Male,
          LocalDate.now.minusYears(20),
          170.5d,
          150.5d,
          130.5d,
          VeryActive,
          None,
          None,
          None,
          None,
          None
        )

        when(mockCache.get[MaleOrFemale]("sex"))
          .thenReturn(Future.successful(Some(Male)))

        when(mockCache.get[LocalDate]("age"))
          .thenReturn(Future.successful(Some(LocalDate.now.minusYears(20))))

        when(mockCache.get[Double]("height"))
          .thenReturn(Future.successful(Some(170.5d)))

        when(mockCache.get[Double]("currentWeight"))
          .thenReturn(Future.successful(Some(150.5d)))

        when(mockCache.get[Double]("targetWeight"))
          .thenReturn(Future.successful(Some(130.5d)))

        when(mockCache.get[ActivityLevel]("activityLevel"))
          .thenReturn(Future.successful(Some(VeryActive)))

        sut.cacheToShortDto mustBe Some(macroCalcDto)
      }

      "return a MacroCalcDto with Options set as None even if Option values exist" in {
        val macroCalcDto: MacroCalcDto = new MacroCalcDto(
          Male,
          LocalDate.now.minusYears(20),
          170.5d,
          150.5d,
          130.5d,
          VeryActive,
          None,
          None,
          None,
          None,
          None
        )

        when(mockCache.get[MaleOrFemale]("sex"))
          .thenReturn(Future.successful(Some(Male)))

        when(mockCache.get[LocalDate]("age"))
          .thenReturn(Future.successful(Some(LocalDate.now.minusYears(20))))

        when(mockCache.get[Double]("height"))
          .thenReturn(Future.successful(Some(170.5d)))

        when(mockCache.get[Double]("currentWeight"))
          .thenReturn(Future.successful(Some(150.5d)))

        when(mockCache.get[Double]("targetWeight"))
          .thenReturn(Future.successful(Some(130.5d)))

        when(mockCache.get[ActivityLevel]("activityLevel"))
          .thenReturn(Future.successful(Some(VeryActive)))

        when(mockCache.get[Int]("kcalGoal"))
          .thenReturn(Future.successful(Some(2000)))

        when(mockCache.get[Int]("proteinGoal"))
          .thenReturn(Future.successful(Some(150)))

        when(mockCache.get[Int]("fatGoal"))
          .thenReturn(Future.successful(Some(50)))

        when(mockCache.get[Int]("carbGoal"))
          .thenReturn(Future.successful(Some(200)))

        when(mockCache.get[Double]("bodyFat"))
          .thenReturn(Future.successful(Some(15)))

        sut.cacheToShortDto mustBe Some(macroCalcDto)
      }

      "return a None if values do not exist" in {

        when(mockCache.get[MaleOrFemale]("sex"))
          .thenReturn(Future.successful(None))

        when(mockCache.get[LocalDate]("age"))
          .thenReturn(Future.successful(None))

        when(mockCache.get[Double]("height"))
          .thenReturn(Future.successful(None))

        when(mockCache.get[Double]("currentWeight"))
          .thenReturn(Future.successful(None))

        when(mockCache.get[Double]("targetWeight"))
          .thenReturn(Future.successful(None))

        when(mockCache.get[ActivityLevel]("activityLevel"))
          .thenReturn(Future.successful(None))

        sut.cacheToShortDto mustBe None
      }
    }

    "cacheToFullDto is called" must {
      "return a full MacroCalcDto if all required values exist" in {
        val macroCalcDto: MacroCalcDto = new MacroCalcDto(
          Male,
          LocalDate.now.minusYears(20),
          170.5d,
          150.5d,
          130.5d,
          VeryActive,
          Some(2000),
          Some(150),
          Some(50),
          Some(200),
          Some(15)
        )

        when(mockCache.get[MaleOrFemale]("sex"))
          .thenReturn(Future.successful(Some(Male)))

        when(mockCache.get[LocalDate]("age"))
          .thenReturn(Future.successful(Some(LocalDate.now.minusYears(20))))

        when(mockCache.get[Double]("height"))
          .thenReturn(Future.successful(Some(170.5d)))

        when(mockCache.get[Double]("currentWeight"))
          .thenReturn(Future.successful(Some(150.5d)))

        when(mockCache.get[Double]("targetWeight"))
          .thenReturn(Future.successful(Some(130.5d)))

        when(mockCache.get[ActivityLevel]("activityLevel"))
          .thenReturn(Future.successful(Some(VeryActive)))

        when(mockCache.get[Int]("kcalGoal"))
          .thenReturn(Future.successful(Some(2000)))

        when(mockCache.get[Int]("proteinGoal"))
          .thenReturn(Future.successful(Some(150)))

        when(mockCache.get[Int]("fatGoal"))
          .thenReturn(Future.successful(Some(50)))

        when(mockCache.get[Int]("carbGoal"))
          .thenReturn(Future.successful(Some(200)))

        when(mockCache.get[Double]("bodyFat"))
          .thenReturn(Future.successful(Some(15)))

        sut.cacheToFullDto mustBe Some(macroCalcDto)
      }

      "return a None if values do not exist" in {

        when(mockCache.get[MaleOrFemale]("sex"))
          .thenReturn(Future.successful(None))

        when(mockCache.get[LocalDate]("age"))
          .thenReturn(Future.successful(None))

        when(mockCache.get[Double]("height"))
          .thenReturn(Future.successful(None))

        when(mockCache.get[Double]("currentWeight"))
          .thenReturn(Future.successful(None))

        when(mockCache.get[Double]("targetWeight"))
          .thenReturn(Future.successful(None))

        when(mockCache.get[ActivityLevel]("activityLevel"))
          .thenReturn(Future.successful(None))

        when(mockCache.get[Int]("kcalGoal"))
          .thenReturn(Future.successful(None))

        when(mockCache.get[Int]("proteinGoal"))
          .thenReturn(Future.successful(None))

        when(mockCache.get[Int]("fatGoal"))
          .thenReturn(Future.successful(None))

        when(mockCache.get[Int]("carbGoal"))
          .thenReturn(Future.successful(None))

        when(mockCache.get[Double]("bodyFat"))
          .thenReturn(Future.successful(None))

        sut.cacheToFullDto mustBe None
      }

    }

  }
}
