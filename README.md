# CommerceApp

## 주제
- 가구/인테리어 관련 커머스 앱
- 선정 이유 :
    - 커뮤니티 및 라이브 커머스 기능을 가진 서비스로 확장 시킬 계획
        - 커뮤니티성 기능 및 커머스 기능을 갖춘 오늘의 집 벤치마킹
- 목적 :
     - 확장성을 고려한 설계와 좋은 코드 작성 방법을 익히는 것을 목표로 개발
     - jetpack compose + coroutine + flow 를 이용한 상태관리
     - 테스트 코드를 작성해보고 테스트하기 좋은 코드에 대해 생각해보기
     - CI/CD 환경 구축해보기

## Screenshots
<img src="https://github.com/hyun132/Algorithm-With-Kotlin/assets/46836642/b2f58b23-0953-4ab0-a88f-7c6be1d852aa" width="180"/>
<img src="https://github.com/hyun132/Algorithm-With-Kotlin/assets/46836642/82d3ceff-09a7-45e2-b750-890ccd244f85" width="180"/>
<img src="https://github.com/hyun132/Algorithm-With-Kotlin/assets/46836642/0b34efdc-b2e9-4f60-8d40-29a3d2c9250e" width="180"/>

# Architecture
- 안드로이드에서 권장하는 [아키텍처](https://developer.android.com/topic/architecture/intro?_gl=1*hmaj30*_up*MQ..*_ga*MTY2OTE5MDI1Ny4xNzA4MjgzNDEy*_ga_6HH9YJMN9M*MTcwODI4MzQxMi4xLjAuMTcwODI4NDgzMy4wLjAuMA..) :  앱의 확장에 용이하고, 데이터 흐름을 단방향으로 관리하여 변경을 추적하기 좋음.

### UI layer
<img src="https://developer.android.com/static/topic/libraries/architecture/images/mad-arch-ui-udf.png" width="250"/>

### Domain layer

### Data layer architecture
<img src="https://developer.android.com/static/topic/libraries/architecture/images/mad-arch-data-overview.png" width="250"/>

참조 : [Recommendations for Android architecture](https://developer.android.com/topic/architecture/recommendations?_gl=1*gej0qv*_up*MQ..*_ga*MTQzMzQ2OTY5Ni4xNzA4Mjc5NjU4*_ga_6HH9YJMN9M*MTcwODI3OTY1OC4xLjAuMTcwODI3OTY1OC4wLjAuMA..)

## Tech stack & Open-source libraries

-   [Kotlin](https://kotlinlang.org/)  based,  [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)  +  [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/)  for asynchronous.
- Jetpack
    - [Lifecycle](https://developer.android.com/jetpack/compose/lifecycle?_gl=1*8r5y71*_up*MQ..*_ga*MTIzODQ0Mzc1NC4xNzA4MjgzMjUw*_ga_6HH9YJMN9M*MTcwODI4MzI0OS4xLjAuMTcwODI4MzI0OS4wLjAuMA..): 액티비티 및 프래그먼트의 라이프사이클을 관찰하고 라이프사이클 변경에 따라 UI 상태를 관리합니다.
    -   [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel): UI 관련 데이터를 보관하는 뷰모델은 라이프사이클을 인지하며, 화면 회전과 같은 구성 변경 시에도 데이터를 유지 관리합니다.
    -   [Hilt](https://dagger.dev/hilt/): 의존성 주입 (Dependency Injection)을 위한 라이브러리입니다.
    - [navigation](https://developer.android.com/jetpack/compose/navigation) : 컴포저블 간의 이동을 탐색 구성 요소의 인프라 및 기능을 활용하여 쉽게 구현합니다.
- [Firestore database](https://firebase.google.com/docs/firestore?hl=ko) : 클라우드 파이어스토어는 Apple, Android, 웹 앱에서 기본 SDK를 통해 직접 액세스할 수 있는 클라우드 기반 NoSQL 데이터베이스입니다.
- [Retrofit](https://square.github.io/retrofit/ ) :  안드로이드와 자바를 위한 타입 안전 HTTP 클라이언트입니다.
- [moshi](https://github.com/square/moshi) : 안드로이드, 자바, 코틀린을 위한 최신 JSON 라이브러리입니다. JSON을 자바 및 코틀린 클래스로 쉽게 파싱하도록 지원합니다.
- [Glide](https://github.com/bumptech/glide) : 글라이드는 비디오 정지 이미지, 이미지, 애니메이션 GIF 로딩, 디코딩, 표시를 지원합니다.
- [Timber](https://github.com/JakeWharton/timber) : 팀버는 안드로이드의 기본 Log 클래스 위에 유용한 기능을 제공하는 작고 확장 가능한 API를 가진 로거입니다.
- [Espresso](https://developer.android.com/training/testing/espresso?hl=ko) : 안드로이드 UI 테스트를 위한 프레임워크입니다.
- [MockK](https://mockk.io/) : 테스트 코드를 작성하기 위한 코틀린 Mock 라이브러리이다.

## 브랜치 전략
가장 대표적인 브랜치 전략인 git flow를 사용해보면서 프로덕션 레벨의 이력관리를 경험해보기 위함.
### Git Flow
-   master
    -   제품으로 출시될 수 있는 브랜치
-   develop
    -   다음 출시 버전을 개발하는 브랜치
-   feature
    -   단위 기능을 개발하는 브랜치
-   release
    -   이번 출시 버전을 준비하는 브랜치 
-   hotfix  
    -   출시 버전에서 발생한 크리티컬한 버그를 긴급 수정하는 브랜치

branch를 merge 할 때는 항상 —no-ff 옵션을 붙여서 branch에 대한 기록이 사라지는 것을 방지하는 것이 원칙

-   master에서 develop을 분기
-   개발자들은 develop 브랜치에 자유롭게 커밋
-   기능 구현이 있는 경우 develop에서 feature-* 브랜치를 분기
-   배포를 준비하기 위해 develop에서 release-* 브랜치를 분기
-   테스트를 진행하면서 발생하는 버그 수정은 release-* 브랜치에 직접 수정 및 반영
-   테스트가 완료되면 release 브랜치를 master와 develop에 merge

## 주요 기능
- 상품
    - 상품조회
    - 검색
    - 장바구니
