# CommerceApp

## 주제
- 가구/인테리어 관련 커머스 앱
- 선정 이유 :
    - 커뮤니티 및 라이브 커머스 기능을 가진 서비스로 확장 시킬 계획
        - 커뮤니티성 기능 및 커머스 기능을 갖춘 오늘의 집 벤치마킹
-	목적 :
     - 확장성을 고려한 설계와 좋은 코드 작성 방법을 익히는 것을 목표로 개발
     - jetpack compose + coroutine + flow 를 이용한 상태관리

## Screenshots

![appui](https://github.com/hyun132/Algorithm-With-Kotlin/assets/46836642/d8922268-bf42-43d1-b8c2-e5ff127e2727)


# Architecture
- 안드로이드에서 권장하는 [아키텍처](https://developer.android.com/topic/architecture/intro?_gl=1*hmaj30*_up*MQ..*_ga*MTY2OTE5MDI1Ny4xNzA4MjgzNDEy*_ga_6HH9YJMN9M*MTcwODI4MzQxMi4xLjAuMTcwODI4NDgzMy4wLjAuMA..) :  앱의 확장에 용이하고, 데이터 흐름을 단방향으로 관리하여 변경을 추적하기 ㅎ
### UI layer
![Application data flows from the data layer to the ViewModel. UI state
flows from the ViewModel to the UI elements, and events flow from the UI
elements back to the ViewModel.](https://developer.android.com/static/topic/libraries/architecture/images/mad-arch-ui-udf.png)

### Domain layer

### Data layer architecture
![In a typical architecture, the data layer's repositories provide data
to the rest of the app and depend on the data sources.](https://developer.android.com/static/topic/libraries/architecture/images/mad-arch-data-overview.png)

참조 : [Recommendations for Android architecture](https://developer.android.com/topic/architecture/recommendations?_gl=1*gej0qv*_up*MQ..*_ga*MTQzMzQ2OTY5Ni4xNzA4Mjc5NjU4*_ga_6HH9YJMN9M*MTcwODI3OTY1OC4xLjAuMTcwODI3OTY1OC4wLjAuMA..)

## Tech stack & Open-source libraries

-   [Kotlin](https://kotlinlang.org/)  based,  [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)  +  [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/)  for asynchronous.
- Jetpack
    - [Lifecycle](https://developer.android.com/jetpack/compose/lifecycle?_gl=1*8r5y71*_up*MQ..*_ga*MTIzODQ0Mzc1NC4xNzA4MjgzMjUw*_ga_6HH9YJMN9M*MTcwODI4MzI0OS4xLjAuMTcwODI4MzI0OS4wLjAuMA..): Observe Android lifecycles and handle UI states upon the lifecycle changes.
    -   ViewModel: Manages UI-related data holder and lifecycle aware. Allows data to survive configuration changes such as screen rotations.
    -   [Hilt](https://dagger.dev/hilt/): for dependency injection.
        There are two types of synchronization and they can complement each other:
    - [navigation](https://developer.android.com/jetpack/compose/navigation) : navigate between composables while taking advantage of the Navigation component's infrastructure and features.

- [Firestore database](https://firebase.google.com/docs/firestore?hl=ko) : Cloud Firestore is a cloud-hosted, NoSQL database that your Apple, Android, and web apps can access directly via native SDKs
- [Retrofit](https://square.github.io/retrofit/ ) :  A type-safe  **HTTP client**  for Android and Java
- [moshi](https://github.com/square/moshi) : Moshi is a modern JSON library for Android, Java and Kotlin. It makes it easy to parse JSON into Java and Kotlin classes
- [Glide](https://github.com/bumptech/glide) : Glide supports fetching, decoding, and displaying video stills, images, and animated GIFs
- [Timber](https://github.com/JakeWharton/timber) : This is a logger with a small, extensible API which provides utility on top of Android's normal `Log` class.

## 브랜치 전략
가장 대표적인 브랜치 전략인 git flow를 사용해보면서
### Git Flow (option 1)
-   master
    -   제품으로 출시될 수 있는 브랜치
-   develop
    -   다음 출시 버전을 개발하는 브랜치
-   feature
    -   단위 기능을 개발하는 브랜치
        ~~ -   release ~~
        ~~    -   이번 출시 버전을 준비하는 브랜치 ~~
        ~~ -   hotfix  ~~
        ~~    -   출시 버전에서 발생한 크리티컬한 버그를 긴급 수정하는 브랜치 ~~

branch를 merge 할 때는 항상 —no-ff 옵션을 붙여서 branch에 대한 기록이 사라지는 것을 방지하는 것이 원칙

-   master에서 develop을 분기
-   개발자들은 develop 브랜치에 자유롭게 커밋
-   기능 구현이 있는 경우 develop에서 feature-* 브랜치를 분기
-   배포를 준비하기 위해 develop에서 release-* 브랜치를 분기
-   테스트를 진행하면서 발생하는 버그 수정은 release-* 브랜치에 직접 수정 및 반영
-   테스트가 완료되면 release 브랜치를 master와 develop에 merge

### Gitlab Flow (option 2)
복잡한 Git Flow와 너무 간단한 Github Flow의 절충안
-   master
-   pre-prodution
    -   production으로 넘어가기 전의 브랜치
    -   테스트 등을 담당
-   production
    -   배포만을 담당
-   새로운 브랜치
    -   github flow의 새로운 브랜치처럼 사용

## 주요 기능
- 회원
    - 회원가입
    - 정보수정
    - 장바구니
- 상품
    - 상품조회
    - 검색
- 결제
    - 주문/결제
- 리뷰