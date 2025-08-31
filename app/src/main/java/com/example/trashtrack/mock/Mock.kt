package com.example.trashtrack.mock

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.example.trashtrack.R

object Mock {

    private val greenColor = Color(0xFF15803D)
    private val blackColor = Color.Black

    private val text = buildAnnotatedString {
        withStyle(style = SpanStyle(color = blackColor)) {
            append("Добро Пожаловать на Trash")
        }
        withStyle(style = SpanStyle(color = greenColor)) {
            append("Track")
        }
        withStyle(style = SpanStyle(color = blackColor)) {
            append("!")
        }
    }

    val demoIntroduction = listOf<DataClasses.Introduction>(


        DataClasses.Introduction(
            backButtonVisible = false,
            skipButtonVisible = true,
            title = text,
            image = R.drawable.scale_12004,
            heading = "Легко избавьтесь от мусора",
            underHeading = "закажите услуги по вывозу мусора по требованию или по расписанию с отслеживанием в реальном времени.",
            nextButtonVisible = false
        ),
        DataClasses.Introduction(
            backButtonVisible = true,
            skipButtonVisible = true,
            title = buildAnnotatedString {
                withStyle(SpanStyle(color = blackColor)) {
                    append("Отслеживайте свои грузовики")
                }
            },
            image = R.drawable.scale_12003,
            heading = "Узнайте, где находится ваш грузовик",
            underHeading = "Отслеживайте своего водителя в режиме реального времени",
            nextButtonVisible = false
        ),
        DataClasses.Introduction(
            backButtonVisible = true,
            skipButtonVisible = true,
            title = buildAnnotatedString {
                withStyle(SpanStyle(color = blackColor)) {
                    append("Простые заказы и платежи")
                }
            },
            image = R.drawable.scale_12002,
            heading = "Заказ по требованию",
            underHeading = "Легко выбирайте услуги, указывайте время и осуществляйте оплату всего за несколько кликов.",
            nextButtonVisible = false
        ),
        DataClasses.Introduction(
            backButtonVisible = true,
            skipButtonVisible = false,
            title = buildAnnotatedString {
                withStyle(SpanStyle(color = blackColor)) {
                    append("Давайте начнём!")
                }
            },
            image = R.drawable.scale_12001,
            heading = "",
            underHeading = "Присоединяйтесь к нам, чтобы получить доступ к простым способам избавления от мусора и отслеживанию ваших заказов в реальном времени!",
            nextButtonVisible = true
        ),

        )

    val demoNews = listOf<DataClasses.NewsMain>(
        DataClasses.NewsMain(
            id = 0,
            nameNews = "Новый экологичный сервис по вывозу мусора!",
            image = R.drawable.dacant_news01,
            secondImage = R.drawable.dacant_news04,
            descriptionNews = """
                <div class="frame-1439">
                  <div class="frame-1438">
                    <div class="div2">
                      Новости от компании Дакант
                      <br />
                      <br />
                      Мы рады сообщить, что компания Дакант запускает новый экологичный сервис
                      по вывозу мусора! ♻️
                      <br />
                      <br />
                      С понедельника мы внедряем новые специальные контейнеры для раздельного
                      сбора отходов в нашем городе. Это шаг к более чистому и зеленому будущему
                      для нас всех. Мы прилагаем максимум усилий для улучшения экологии и
                      призываем наших клиентов активно участвовать в раздельном сборе.
                      <br />
                      <br />
                      Кроме того, в сентябре стартует акция &quot;Чистый двор&quot;, где каждый
                      житель может получить скидки на вывоз мусора, участвуя в уборке своей
                      территории.
                      <br />
                      <br />
                      Присоединяйтесь к нашей инициативе и помогите сделать наш город чище!
                      Следите за новостями и обновлениями на нашем сайте и в социальных сетях.
                      Вместе с Дакант мы можем сделать многое! 🌱✨
                    </div>
                  </div>
                </div>
            """.trimIndent()
        ),
        DataClasses.NewsMain(
            id = 1,
            nameNews = "Ищем сотрудников в нашу дружную команду",
            image = R.drawable.dacant_news04,
            secondImage = R.drawable.dacant_news03,
            descriptionNews = """
                <div class="frame-1439">
                  <div class="frame-1438">
                    <div class="trash-track-trash-track-info-trashtrack-com-trash-track">
                      Внимание! Вакансия в TrashTrack!
                      <br />
                      <br />
                      Мы, компания TrashTrack, ищем людей, которые хотят присоединиться к нашей
                      команде и помочь сделать мир чище!
                      <br />
                      <br />
                      Что мы предлагаем:
                      <br />
                      - Интересную и динамичную работу в дружном коллективе
                      <br />
                      - Возможности для обучения и развития
                      <br />
                      <br />
                      Требования:
                      <br />
                      - Ответственность и желание работать на результат
                      <br />
                      - Умение работать в команде
                      <br />
                      - Опыт работы в сфере экологии будет плюсом
                      <br />
                      <br />
                      Если ты готов стать частью нашей миссии по охране окружающей среды,
                      отправляй свое резюме на почту info@trashtrack.com!
                      <br />
                      Присоединяйся к нам и сделай мир лучше вместе с TrashTrack! 🌍✨
                    </div>
                  </div>
                </div>
            """.trimIndent()
        ),
        DataClasses.NewsMain(
            id = 2,
            nameNews = "Выгодней не найдешь",
            image = R.drawable.dacant_news03,
            secondImage = R.drawable.dacant_news02,
            descriptionNews = """
                <div class="frame-1439">
                  <div class="frame-1438">
                    <div class="trash-track-1-15-40">
                      <span>
                        <span class="trash-track-1-15-40-span">Суперакция от</span>
                        <span class="trash-track-1-15-40-span2">Trash</span>
                        <span class="trash-track-1-15-40-span3">
                          Track
                          <br />
                          <br />
                          Всем любителям чистоты и порядка отличная новость! 🎉
                          <br />
                          <br />
                          Только с 1 по 15 ноября компания Дакант предлагает скидку 40% на все
                          услуги по вывозу мусора! Это отличный шанс избавиться от ненужных
                          отходов по супервыгодной цене.
                          <br />
                          <br />
                          Не упустите возможность сделать ваш дом и двор чище, экономя деньги.
                          Просто позвоните нам или оставьте заявку на сайте, и наши специалисты
                          быстро организуют вывоз.
                          <br />
                          <br />
                          Не ждите, акция ограничена! Делитесь новостью с друзьями - чистота
                          должна быть доступна каждому! 🗑✨
                        </span>
                      </span>
                    </div>
                  </div>
                </div>
            """.trimIndent()
        ),
        DataClasses.NewsMain(
            id = 3,
            nameNews = "Мы первые ...",
            image = R.drawable.dacant_news02,
            secondImage = R.drawable.dacant_news01,
            descriptionNews = """
                <div class="frame-1438">
                  <div class="div2">
                    Мы первые в инновациях! 🚀
                    <br />
                    С гордостью сообщаем, что наша компания стала первым в регионе поставщиком
                    новейших технологий в сфере экологии! Мы запустили уникальную программу по
                    утилизации отходов с использованием передовых методов переработки.
                    <br />
                    <br />
                    Наша команда разработала ребрендинг системы сбора мусора, внедрив
                    инновационные контейнеры с умным отслеживанием заполненности. Теперь вы
                    можете легко отслеживать уровень отходов и заказывать вывоз прямо с вашего
                    смартфона!
                    <br />
                    <br />
                    Эти изменения позволят существенно сократить время на уборку и повысить
                    экологическую сознательность среди горожан. Мы уверены, что станем
                    вдохновляющим примером для других компаний.
                    <br />
                    <br />
                    Присоединяйтесь к нам в этом важном движении за чистую планету и следите за
                    нашими новостями! 🌍💚
                  </div>
                </div>
            """.trimIndent()
        ),
    )

    val demoKgAndL = listOf<DataClasses.KgAndL>(
        DataClasses.KgAndL(
            kg = 5,
            l = 35
        ),
        DataClasses.KgAndL(
            kg = 10,
            l = 70
        ),
        DataClasses.KgAndL(
            kg = 20,
            l = 105
        )
    )


    val white = Color(0xFFFFFFFF)
    val neutral950 = Color(0xFF0A0A0A)
    val neutral500 = Color(0xFF737373)
    val green600 = Color(0xFF16A34A)
    val green500 = Color(0xFF22C55E)

    val demoSubscriptionData = listOf<DataClasses.SubscriptionData>(


        DataClasses.SubscriptionData(
            id = 0,
            benefit = "",
            heading = "Разовый взнос 150 ₽",
            underHeading = "",
            money = 150,
            price = 0,
            visible = false,
            background = white,
            border = neutral500,
            text = neutral950,
        ),
        DataClasses.SubscriptionData(
            id = 1,
            benefit = "Выгода 80%",
            heading = "Первая подписка",
            underHeading = "1 месяц вывез мусора, каждый понедельник",
            money = 200,
            price = 1500,
            visible = true,
            background = green600,
            border = green500,
            text = white,
        ),
        DataClasses.SubscriptionData(
            id = 2,
            benefit = "Выгода до 40%",
            heading = "Подписка на 6 месяцев",
            underHeading = "Оплата на 6 месяцев по выгодной цене, и регулярный вывез мусора, либо по вашему графику",
            price = 0,
            money = 4000,
            visible = true,
            background = white,
            border = neutral500,
            text = neutral950,
        ),
        DataClasses.SubscriptionData(
            id = 3,
            benefit = "Выгода до 35%",
            heading = "Подписка на 1 год",
            underHeading = "Оплата на год по выгодной цене, и регулярный вывез мусора, либо по вашему графику",
            price = 0,
            money = 7000,
            visible = true,
            background = white,
            border = neutral500,
            text = neutral950,
        ),
    )
}