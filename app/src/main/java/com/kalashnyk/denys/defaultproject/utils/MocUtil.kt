package com.kalashnyk.denys.defaultproject.utils

import com.kalashnyk.denys.defaultproject.presentation.navigation.fragment_navigator.model.Pages
import com.kalashnyk.denys.defaultproject.presentation.widget.pageview.model.TabPages
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.*

object MocUtil {

    fun mocListThemes(): List<ThemeEntity> {

        val list=ArrayList<ThemeEntity>()
        val mocArticleShortDescription = "Journalists utilize the Who, What, Where, When, Why and How method for getting across the facts of their stories, and following this process is the first step in crafting a compelling product description."
        val mocEventShortDescription = "Setting up your free Eventbrite page is very simple when it comes to the technical side of things… but you’re not alone if you find the thought of writing copy for your event a bit daunting."

        val imageUrl1 = "https://www.cclep.eu/wp-content/uploads/2018/05/cropped-banner1-3.jpg"
        val entity1=ThemeEntity(1, CARD_EVENT, ("$CARD_EVENT item 1"), imageUrl1, mocEventShortDescription, ("$CARD_EVENT category 1")).apply { this.screenType = TabPages.TAB_FEED.name }
        list.add(entity1)
        val imageUrl2 = "https://images.unsplash.com/photo-1508138221679-760a23a2285b?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80"
        val entity2=ThemeEntity(2, CARD_ARTICLE, ("Plane article"), imageUrl2, mocArticleShortDescription, ("$CARD_ARTICLE category 2")).apply { this.screenType = TabPages.TAB_FEED.name }
        list.add(entity2)
        val imageUrl3 = "https://lh3.googleusercontent.com/proxy/BltRcVdlBglEu-rbo8G8LVLjhY-TuxWCm1jkxp6q6683TdL6rWvbqppJEqrSOcMBk2aHk0IBws2XzIFMBDk2OQSRMCBy89rRYF_n5rt2HX3XKVEq4vA9lEHwJYZRXIA"
        val entity3=ThemeEntity(3, CARD_EVENT, ("$CARD_EVENT item 3"), imageUrl3, mocEventShortDescription, ("$CARD_EVENT category 3")).apply { this.screenType = TabPages.TAB_FEED.name }
        list.add(entity3)
        val imageUrl4 = "https://www.rako.cz/file/edee/realizace/random/v_random2_7168.jpg"
        val entity4=ThemeEntity(4, CARD_ARTICLE, ("Interier article"), imageUrl4, mocArticleShortDescription, ("$CARD_ARTICLE category 4")).apply { this.screenType = TabPages.TAB_FEED.name }
        list.add(entity4)
        val imageUrl5 = "https://www.comecreationsgroup.pl/wp/wp-content/uploads/2016/01/event.jpg"
        val entity5=ThemeEntity(5, CARD_EVENT, ("$CARD_EVENT item 5"), imageUrl5, mocEventShortDescription, ("$CARD_EVENT category 5")).apply { this.screenType = TabPages.TAB_FEED.name }
        list.add(entity5)
        val imageUrl6 = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRKsJoGKlOJnxl-GNgfUtluGobgx_M8JBdsng&usqp=CAU"
        val entity6=ThemeEntity(6, CARD_ARTICLE, ("Mouse article"), imageUrl6, mocArticleShortDescription, ("$CARD_ARTICLE category 6")).apply { this.screenType = TabPages.TAB_FEED.name }
        list.add(entity6)
        val imageUrl7 = "https://lh3.googleusercontent.com/proxy/i7d-1IvPmRwKvfpHl60mDo6Y20eJW23A21_Qx5XFM51YFLYRWXHgpV7WNPs0dWlBxMCeYW8e3NA6gbWDsuNtxfk4bOVdiUM"
        val entity7=ThemeEntity(7, CARD_EVENT, ("$CARD_EVENT item 7"), imageUrl7, mocEventShortDescription, ("$CARD_EVENT category 7")).apply { this.screenType = TabPages.TAB_FEED.name }
        list.add(entity7)
        val imageUrl8 = "https://www.hackingwithswift.com/uploads/matrix.jpg"
        val entity8=ThemeEntity(8, CARD_ARTICLE, ("Hacking article"), imageUrl8, mocArticleShortDescription, ("$CARD_ARTICLE category 8")).apply { this.screenType = TabPages.TAB_FEED.name }
        list.add(entity8)
        val imageUrl9 = "https://www.konferencje.pl/media/cache/article_horizontal/a/4/d/a/a4da205cb484d7f4b0e6b9f2b0f1680a.jpg"
        val entity9=ThemeEntity(9, CARD_EVENT, ("$CARD_EVENT item 9"), imageUrl9, mocEventShortDescription, ("$CARD_EVENT category 9")).apply { this.screenType = TabPages.TAB_FEED.name }
        list.add(entity9)
        val imageUrl10 = "https://www.mandysam.com/img/random.jpg"
        val entity10=ThemeEntity(10, CARD_ARTICLE, ("Looking article"), imageUrl10, mocArticleShortDescription, ("$CARD_ARTICLE category 10")).apply { this.screenType = TabPages.TAB_FEED.name }
        list.add(entity10)
        val imageUrl11 = "https://megajet.home.pl/autoinstalator/wordpressplugins1/wp-content/uploads/2020/01/event-Lagerdere-135.jpg"
        val entity11=ThemeEntity(11, CARD_EVENT, ("$CARD_EVENT item 11"), imageUrl11, mocEventShortDescription, ("$CARD_EVENT category 11")).apply { this.screenType = TabPages.TAB_FEED.name }
        list.add(entity11)
        val imageUrl12 = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSUPiYB8dCdTJN7DTmcliXhmof21Wdy0RIqng&usqp=CAU"
        val entity12=ThemeEntity(12, CARD_ARTICLE, ("Security article"), imageUrl12, mocArticleShortDescription, ("$CARD_ARTICLE category 12")).apply { this.screenType = Pages.CREATED_THEMES.text }
        list.add(entity12)
        val imageUrl13 = "https://www.event360.pl/upload/maintail/event360_oprawa_muzyczna_koncerty_artysci_agencja_eventowa_slask.jpg"
        val entity13=ThemeEntity(13, CARD_EVENT, ("$CARD_EVENT item 13"), imageUrl13, mocEventShortDescription, ("$CARD_EVENT category 12")).apply { this.screenType = Pages.CREATED_THEMES.text }
        list.add(entity13)
        val imageUrl14 = "https://jadziajedzie.files.wordpress.com/2020/05/img_20190824_113949-2.jpg?w=870&h=773&crop=1"
        val entity14=ThemeEntity(14, CARD_ARTICLE, ("Travel article"), imageUrl14, mocArticleShortDescription, ("$CARD_ARTICLE category 14")).apply { this.screenType = Pages.CREATED_THEMES.text }
        list.add(entity14)
        val imageUrl15 = "https://www.argentum-event.pl/wp-content/uploads/2019/05/O-nas-zesp%C3%B3%C5%82-Argentum-Event-4.jpg"
        val entity15=ThemeEntity(15, CARD_EVENT, ("$CARD_EVENT item 15"), imageUrl15, mocEventShortDescription, ("$CARD_EVENT category 15")).apply { this.screenType = Pages.CREATED_THEMES.text }
        list.add(entity15)
        val imageUrl16 = "https://media-cdn.tripadvisor.com/media/photo-s/19/9e/c0/32/random-restaurant.jpg"
        val entity16=ThemeEntity(16, CARD_ARTICLE, ("Food article"), imageUrl16, mocArticleShortDescription, ("$CARD_ARTICLE category 16")).apply { this.screenType = Pages.CREATED_THEMES.text }
        list.add(entity16)
        val imageUrl17 = "https://imagazine.pl/wp-content/uploads/2020/09/Apple-Special-Event-September-2020.jpg"
        val entity17=ThemeEntity(17, CARD_EVENT, ("Apple event"), imageUrl17, mocEventShortDescription, ("$CARD_EVENT category 17")).apply { this.screenType = Pages.CREATED_THEMES.text }
        list.add(entity17)
        val imageUrl18 = "https://www.thebalanceeveryday.com/thmb/8ycx18oHjPPqILoqMri0tKwoVm8=/735x0/Random-Drawing-by-Materio-GettyImages-95442265-5b4ba4ff46e0fb00378f364a.jpg"
        val entity18=ThemeEntity(18, CARD_ARTICLE, ("Choice article"), imageUrl18, mocArticleShortDescription, ("$CARD_ARTICLE category 18")).apply { this.screenType = Pages.FOLLOWED_THEMES.text }
        list.add(entity18)
        val imageUrl19 = "https://gamingsociety.pl/wp-content/uploads/2020/06/Fortnite-letni-event.jpg"
        val entity19=ThemeEntity(19, CARD_EVENT, ("Fortnite event"), imageUrl19, mocEventShortDescription, ("$CARD_EVENT category 19")).apply { this.screenType = Pages.FOLLOWED_THEMES.text }
        list.add(entity19)
        val imageUrl20 = "https://s26552.pcdn.co/wp-content/uploads/2020/04/dc_neighborhood_news-11.jpg"
        val entity20=ThemeEntity(20, CARD_ARTICLE, ("Dog article"), imageUrl20, mocArticleShortDescription, ("$CARD_ARTICLE category 20")).apply { this.screenType = Pages.FOLLOWED_THEMES.text }
        list.add(entity20)
        val imageUrl21 = "https://koszalininfo.pl/wp-content/uploads/2019/09/event-800x445.jpg"
        val entity21=ThemeEntity(21, CARD_EVENT, ("Line event"), imageUrl21, mocEventShortDescription, ("$CARD_EVENT category 21")).apply { this.screenType = Pages.FOLLOWED_THEMES.text }
        list.add(entity21)
        val imageUrl22 = "https://randomseed.pl/pod/randomself/random-self.jpg"
        val entity22=ThemeEntity(22, CARD_ARTICLE, ("Theater article"), imageUrl22, mocArticleShortDescription, ("$CARD_ARTICLE category 22")).apply { this.screenType = Pages.FOLLOWED_THEMES.text }
        list.add(entity22)
        val imageUrl23 = "https://www.cclep.eu/wp-content/uploads/2018/05/cropped-banner1-3.jpg"
        val entity23=ThemeEntity(23, CARD_EVENT, ("$CARD_EVENT item 23"), imageUrl23, mocEventShortDescription, ("$CARD_EVENT category 23")).apply { this.screenType = Pages.FOLLOWED_THEMES.text }
        list.add(entity23)
        val imageUrl24 = "https://mlinpl.pl/wp-content/uploads/2019/11/algorytm-random-forest-.jpg"
        val entity24=ThemeEntity(24, CARD_ARTICLE, ("Nature article"), imageUrl24, mocArticleShortDescription, ("$CARD_ARTICLE category 24")).apply { this.screenType = Pages.FOLLOWED_THEMES.text }
        list.add(entity24)
        val imageUrl25 = "https://www.event360.pl/upload/maintail/event360_oprawa_muzyczna_koncerty_artysci_agencja_eventowa_slask.jpg"
        val entity25=ThemeEntity(25, CARD_EVENT, ("$CARD_EVENT item 25"), imageUrl25, mocEventShortDescription, ("$CARD_EVENT category 25")).apply { this.screenType = Pages.FOLLOWED_THEMES.text }
        list.add(entity25)

        return list
    }

    fun mocListUsers(): List<UserEntity> {
        val occupation = "Developer"
        val location = LocationEntity(300, "USA", "Taxas", "Dallas")

        val list=ArrayList<UserEntity>()
        val avatar1 = "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
        val entity1=UserEntity(1, ("Monique"), ("Searle"), ("$CARD_USER fathername 1"), avatar1).apply {
            this.occupation = occupation
            this.location = location
            this.screenType = TabPages.TAB_PEOPLE.name
        }
        list.add(entity1)
        val avatar2 = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRvzJLVW5kwavoejLWDRWrRIpiHd3vsQY_-uw&usqp=CAU"
        val entity2=UserEntity(2, ("Shaunie"), ("Frost"), ("$CARD_USER fathername 2"), avatar2).apply {
            this.occupation = occupation
            this.location = location
            this.screenType = TabPages.TAB_PEOPLE.name
        }
        list.add(entity2)
        val avatar3 = "https://cdn.fastly.picmonkey.com/contentful/h6goo9gw1hh6/2sNZtFAWOdP1lmQ33VwRN3/24e953b920a9cd0ff2e1d587742a2472/1-intro-photo-final.jpg?w=800&q=70"
        val entity3=UserEntity(3, ("Faye"), ("Wallis"), ("$CARD_USER fathername 3"), avatar3).apply {
            this.occupation = occupation
            this.location = location
            this.screenType = TabPages.TAB_PEOPLE.name
        }
        list.add(entity3)
        val avatar4 = "https://img.freepik.com/darmowe-zdjecie/przystojny-mlody-biznesmen-w-koszula-i-eyeglasses_85574-6228.jpg?size=626&ext=jpg"
        val entity4=UserEntity(4, ("Brenden"), ("Hull"), ("$CARD_USER fathername 4"), avatar4).apply {
            this.occupation = occupation
            this.location = location
            this.screenType = TabPages.TAB_PEOPLE.name
        }
        list.add(entity4)
        val avatar5 = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSyYijt7wsiqCMzExlfV2Eeib8BfYVGL3AjPA&usqp=CAU"
        val entity5=UserEntity(5, ("Sadiyah"), ("Oneal"), ("$CARD_USER fathername 5"), avatar5).apply {
            this.occupation = occupation
            this.location = location
            this.screenType = TabPages.TAB_PEOPLE.name
        }
        list.add(entity5)
        val avatar6 = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRfEC9CHLUYNXZdxal6PVsYtQpbgkRoezEuig&usqp=CAU"
        val entity6=UserEntity(6, ("Mohammed"), ("Russo"), ("$CARD_USER fathername 6"), avatar6).apply {
            this.occupation = occupation
            this.location = location
            this.screenType = TabPages.TAB_PEOPLE.name
        }
        list.add(entity6)
        val avatar7 = "https://www4.pictures.zimbio.com/gi/39th+Annual+People+Choice+Awards+Portraits+lza8vaU_QsZx.jpg"
        val entity7=UserEntity(7, ("Marwan"), ("Wilson"), ("$CARD_USER fathername 7"), avatar7).apply {
            this.occupation = occupation
            this.location = location
            this.screenType = TabPages.TAB_PEOPLE.name
        }
        list.add(entity7)
        val avatar8 = "https://upload.wikimedia.org/wikipedia/en/thumb/a/a1/NafSadh_Profile.jpg/768px-NafSadh_Profile.jpg"
        val entity8=UserEntity(8, ("Siddharth"), ("Kelly"), ("$CARD_USER fathername 8"), avatar8).apply {
            this.occupation = occupation
            this.location = location
            this.screenType = TabPages.TAB_PEOPLE.name
        }
        list.add(entity8)
        val avatar9 = "https://barefootmedia.co.uk/wp-content/uploads/2016/01/Chris-user-profile.jpg"
        val entity9=UserEntity(9, ("Kellie"), ("Boyce"), ("$CARD_USER fathername 9"), avatar9).apply {
            this.occupation = occupation
            this.location = location
            this.screenType = TabPages.TAB_PEOPLE.name
        }
        list.add(entity9)
        val avatar10 = "https://www.outsystems.com/PortalTheme/_image.aspx/uT1GcjtMbee8D5dKHOZuY4cCaFEUquawEeqVl6kEWLk=/2018-07-03%2008-33-53"
        val entity10=UserEntity(10, ("Kien"), ("Watkins"), ("$CARD_USER fathername 10"), avatar10).apply {
            this.occupation = occupation
            this.location = location
            this.screenType = TabPages.TAB_PEOPLE.name
        }
        list.add(entity10)
        val avatar11 = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRrJ2R9O5THIdzGHJl3RjnK2Bxzj20iYSsMQA&usqp=CAU"
        val entity11=UserEntity(11, ("Caolan"), ("Holman"), ("$CARD_USER fathername 11"), avatar11).apply {
            this.occupation = occupation
            this.location = location
            this.screenType = TabPages.TAB_PEOPLE.name
        }
        list.add(entity11)
        val avatar12 = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRch6CDHA9hqbe3GbIo6O0T-EWeIL7JJ8_cpQ&usqp=CAU"
        val entity12=UserEntity(12, ("Humayra"), ("Mcbride"), ("$CARD_USER fathername 12"), avatar12).apply {
            this.occupation = occupation
            this.location = location
            this.screenType = TabPages.TAB_PEOPLE.name
        }
        list.add(entity12)
        val avatar13 = "https://www.2-10.com/wp-content/uploads/2015/10/user1.jpg"
        val entity13=UserEntity(13, ("Sabah"), ("Mccallum"), ("$CARD_USER fathername 13"), avatar13).apply {
            this.occupation = occupation
            this.location = location
            this.screenType = TabPages.TAB_PEOPLE.name
        }
        list.add(entity13)
        val avatar14 = "https://pbs.twimg.com/profile_images/974736784906248192/gPZwCbdS.jpg"
        val entity14=UserEntity(14, ("Isaak"), ("Fenton"), ("$CARD_USER fathername 14"), avatar14).apply {
            this.occupation = occupation
            this.location = location
            this.screenType = TabPages.TAB_PEOPLE.name
        }
        list.add(entity14)
        val avatar15 = "https://www.vernonmorningstar.com/wp-content/uploads/2017/05/web1_Crook-Parker-cookie-2017.jpg"
        val entity15=UserEntity(15, ("Tyra"), ("Herbert"), ("$CARD_USER fathername 15"), avatar15).apply {
            this.occupation = occupation
            this.location = location
            this.screenType = TabPages.TAB_PEOPLE.name
        }
        list.add(entity15)
        val avatar16 = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSWdMBviWKCk0hSEFULTZepZeTLRiB6u5MACg&usqp=CAU"
        val entity16=UserEntity(16, ("Taiba"), ("Oneill"), ("$CARD_USER fathername 16"), avatar16).apply {
            this.occupation = occupation
            this.location = location
            this.screenType = TabPages.TAB_PEOPLE.name
        }
        list.add(entity16)
        val avatar17 = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTj6Ug7sL2WibvAAnPhqpPkU8JWvd_jCQMoRA&usqp=CAU"
        val entity17=UserEntity(17, ("Coby"), ("Bailey"), ("$CARD_USER fathername 17"), avatar17).apply {
            this.occupation = occupation
            this.location = location
            this.screenType = TabPages.TAB_PEOPLE.name
        }
        list.add(entity17)
        val avatar18 = "https://pbs.twimg.com/profile_images/1001332003474567169/vDCiE04W_400x400.jpg"
        val entity18=UserEntity(18, ("Nannie"), ("Gilmore"), ("$CARD_USER fathername 18"), avatar18).apply {
            this.occupation = occupation
            this.location = location
            this.screenType = TabPages.TAB_PEOPLE.name
        }
        list.add(entity18)
        val avatar19 = "https://kamleshyadav.in/wp/affiliate/wp-content/uploads/2018/07/testimonial1.jpg"
        val entity19=UserEntity(19, ("Idris"), ("Travis"), ("$CARD_USER fathername 19"), avatar19).apply {
            this.occupation = occupation
            this.location = location
            this.screenType = TabPages.TAB_PEOPLE.name
        }
        list.add(entity19)
        val avatar20 = "https://www.swindonadvertiser.co.uk/resources/images/11600823/"
        val entity20=UserEntity(20, ("Reuben"), ("Baird"), ("$CARD_USER fathername 20"), avatar20).apply {
            this.occupation = occupation
            this.location = location
            this.screenType = TabPages.TAB_PEOPLE.name
        }
        list.add(entity20)
        val avatar21 = "https://image.freepik.com/darmowe-zdjecie/portret-odizolowywajacy-bialy-mezczyzna_53876-40306.jpg"
        val entity21=UserEntity(21, ("Gurdeep"), ("Hays"), ("$CARD_USER fathername 21"), avatar21).apply {
            this.occupation = occupation
            this.location = location
            this.screenType = TabPages.TAB_PEOPLE.name
        }
        list.add(entity21)
        val avatar22 = "https://image.freepik.com/darmowe-zdjecie/portret-powazny-przystojny-mezczyzna-patrzeje-kamere-przeciw-bialemu-tlu_23-2148213410.jpg"
        val entity22=UserEntity(22, ("Jenny"), ("Vickers"), ("$CARD_USER fathername 22"), avatar22).apply {
            this.occupation = occupation
            this.location = location
            this.screenType = TabPages.TAB_PEOPLE.name
        }
        list.add(entity22)
        val avatar23 = "https://thumbs.dreamstime.com/b/portrait-de-jeune-homme-heureux-de-sourire-38290461.jpg"
        val entity23=UserEntity(23, ("Keri"), ("Frey"), ("$CARD_USER fathername 23"), avatar23).apply {
            this.occupation = occupation
            this.location = location
            this.screenType = TabPages.TAB_PEOPLE.name
        }
        list.add(entity23)
        val avatar24 = "https://tricksofmind.pl/wp-content/uploads/2015/08/normal-huy.jpg"
        val entity24=UserEntity(24, ("Tara"), ("Dorsey"), ("$CARD_USER fathername 24"), avatar24).apply {
            this.occupation = occupation
            this.location = location
            this.screenType = TabPages.TAB_PEOPLE.name
        }
        list.add(entity24)
        val avatar25 = "https://images.unsplash.com/photo-1570295999919-56ceb5ecca61?ixlib=rb-1.2.1&w=1000&q=80"
        val entity25=UserEntity(25, ("Khadija"), ("Daniel"), ("$CARD_USER fathername 25"), avatar25).apply {
            this.occupation = occupation
            this.location = location
            this.screenType = TabPages.TAB_PEOPLE.name
        }
        list.add(entity25)

        return list
    }

    private fun getMessageList(userId: Int): ArrayList<MessageItemEntity> {
        val list=ArrayList<MessageItemEntity>()
        val entity1=MessageItemEntity(1, userId, 0, ("She who arrival end how fertile enabled."))
        list.add(entity1)
        val entity2=MessageItemEntity(2, userId, 0, ("Brother she add yet see minuter natural smiling article painted."))
        list.add(entity2)
        val entity3=MessageItemEntity(3, userId, 0, ("Themselves at dispatched interested insensible am be prosperous reasonably it."))
        list.add(entity3)
        val entity4=MessageItemEntity(4, userId, 0, ("In either so spring wished."))
        list.add(entity4)
        val entity5=MessageItemEntity(5, userId, 0, ("Melancholy way she boisterous use friendship she dissimilar considered expression."))
        list.add(entity5)
        val entity6=MessageItemEntity(6, userId, 0, ("Sex quick arose mrs lived."))
        list.add(entity6)
        val entity7=MessageItemEntity(7, userId, 0, ("Mr things do plenty others an vanity myself waited to."))
        list.add(entity7)
        val entity8=MessageItemEntity(8, userId, 0, ("Always parish tastes at as mr father dining at. "))
        list.add(entity8)
        val entity9=MessageItemEntity(9, userId, 0, ("Surrounded to me occasional pianoforte alteration unaffected impossible ye."))
        list.add(entity9)
        val entity10=MessageItemEntity(10, userId, 0, ("For saw half than cold."))
        list.add(entity10)
        list.shuffle()
        return list
    }

    fun mocListMessages(): List<MessagesEntity> {
        val list=ArrayList<MessagesEntity>()
        val entity1=MessagesEntity(1, 1, "Vanessa Hodgson", getMessageList(1)).apply { this.screenType = TabPages.TAB_MESSAGES.name }
        list.add(entity1)
        val entity2=MessagesEntity(2, 2, "Jagoda Cleveland", getMessageList(2)).apply { this.screenType = TabPages.TAB_MESSAGES.name }
        list.add(entity2)
        val entity3=MessagesEntity(3, 3, "Finley Bowers", getMessageList(3)).apply { this.screenType = TabPages.TAB_MESSAGES.name }
        list.add(entity3)
        val entity4=MessagesEntity(4, 4, "Alison Mcpherson", getMessageList(4)).apply { this.screenType = TabPages.TAB_MESSAGES.name }
        list.add(entity4)
        val entity5=MessagesEntity(5, 5, "Cassian Hartley", getMessageList(5)).apply { this.screenType = TabPages.TAB_MESSAGES.name }
        list.add(entity5)
        val entity6=MessagesEntity(6, 6, "Zaina Daniels", getMessageList(6)).apply { this.screenType = TabPages.TAB_MESSAGES.name }
        list.add(entity6)
        val entity7=MessagesEntity(7, 7, "Kason Harvey", getMessageList(7)).apply { this.screenType = TabPages.TAB_MESSAGES.name }
        list.add(entity7)
        val entity8=MessagesEntity(8, 8, "Christian Lewis", getMessageList(8)).apply { this.screenType = TabPages.TAB_MESSAGES.name }
        list.add(entity8)
        val entity9=MessagesEntity(9, 9, "Suraj Wallace", getMessageList(9)).apply { this.screenType = TabPages.TAB_MESSAGES.name }
        list.add(entity9)
        val entity10=MessagesEntity(10, 10, "Hollie Hill", getMessageList(10)).apply { this.screenType = TabPages.TAB_MESSAGES.name }
        list.add(entity10)
        val entity11=MessagesEntity(11, 11, "Lacie Hollis", getMessageList(11)).apply { this.screenType = TabPages.TAB_MESSAGES.name }
        list.add(entity11)
        val entity12=MessagesEntity(12, 12, "Adnan Braun", getMessageList(12)).apply { this.screenType = TabPages.TAB_MESSAGES.name }
        list.add(entity12)
        val entity13=MessagesEntity(13, 13, "Victoria Whitehead", getMessageList(13)).apply { this.screenType = TabPages.TAB_MESSAGES.name }
        list.add(entity13)
        val entity14=MessagesEntity(14, 14, "Ari Needham", getMessageList(14)).apply { this.screenType = TabPages.TAB_MESSAGES.name }
        list.add(entity14)
        val entity15=MessagesEntity(15, 15, "Sadia Parkinson", getMessageList(15)).apply { this.screenType = TabPages.TAB_MESSAGES.name }
        list.add(entity15)
        val entity16=MessagesEntity(16, 16, "Giuseppe Mcfarland", getMessageList(16)).apply { this.screenType = TabPages.TAB_MESSAGES.name }
        list.add(entity16)
        val entity17=MessagesEntity(17, 17, "Jerry Vo", getMessageList(17)).apply { this.screenType = TabPages.TAB_MESSAGES.name }
        list.add(entity17)
        val entity18=MessagesEntity(18, 18, "Ayaz Ramsay", getMessageList(18)).apply { this.screenType = TabPages.TAB_MESSAGES.name }
        list.add(entity18)
        val entity19=MessagesEntity(19, 19, "Leonard James", getMessageList(19)).apply { this.screenType = TabPages.TAB_MESSAGES.name }
        list.add(entity19)
        val entity20=MessagesEntity(20, 20, "Millie-Mae Mcgill", getMessageList(20)).apply { this.screenType = TabPages.TAB_MESSAGES.name }
        list.add(entity20)
        val entity21=MessagesEntity(21, 21, "Israr Bullock", getMessageList(21)).apply { this.screenType = TabPages.TAB_MESSAGES.name }
        list.add(entity21)
        val entity22=MessagesEntity(22, 22, "Lloyd Parker", getMessageList(22)).apply { this.screenType = TabPages.TAB_MESSAGES.name }
        list.add(entity22)
        val entity23=MessagesEntity(23, 23, "Arlene Cooley", getMessageList(23)).apply { this.screenType = TabPages.TAB_MESSAGES.name }
        list.add(entity23)
        val entity24=MessagesEntity(24, 24, "Esmay Lott", getMessageList(24)).apply { this.screenType = TabPages.TAB_MESSAGES.name }
        list.add(entity24)
        val entity25=MessagesEntity(25, 25, "Kush Jennings", getMessageList(25)).apply { this.screenType = TabPages.TAB_MESSAGES.name }
        list.add(entity25)

        return list
    }

}