package com.pmesa.chiperreddit.repo.source.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.pmesa.chiperreddit.repo.source.cache.RoomSubReddit

class GetContentResponse {
    @SerializedName("data")
    @Expose
    var content: Content? = null
}

class Content {
    @SerializedName("children")
    @Expose
    var subReddits: List<SubReddit>? = null
}

class SubReddit {
    fun toRoom(): RoomSubReddit {
        return RoomSubReddit(childData?.url ?: "", childData?.name, childData?.displayName, childData?.description, childData?.url, childData?.iconImg)
    }

    @SerializedName("kind")
    @Expose
    var kind: String? = null
    @SerializedName("data")
    @Expose
    var childData: ChildData? = null
}

class ChildData {
    @SerializedName("user_flair_background_color")
    @Expose
    var userFlairBackgroundColor: Any? = null
    @SerializedName("submit_text_html")
    @Expose
    var submitTextHtml: String? = null
    @SerializedName("restrict_posting")
    @Expose
    var restrictPosting = false
    @SerializedName("user_is_banned")
    @Expose
    var userIsBanned: Any? = null
    @SerializedName("free_form_reports")
    @Expose
    private var freeFormReports = false
    @SerializedName("wiki_enabled")
    @Expose
    private var wikiEnabled = false
    @SerializedName("user_is_muted")
    @Expose
    private var userIsMuted: Any? = null
    @SerializedName("user_can_flair_in_sr")
    @Expose
    private var userCanFlairInSr: Any? = null
    @SerializedName("display_name")
    @Expose
    var displayName: String? = null
    @SerializedName("header_img")
    @Expose
    var headerImg: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("icon_size")
    @Expose
    private var iconSize: List<Long>? = null
    @SerializedName("primary_color")
    @Expose
    private var primaryColor: String? = null
    @SerializedName("active_user_count")
    @Expose
    private var activeUserCount: Any? = null
    @SerializedName("icon_img")
    @Expose
    var iconImg: String? = null
    @SerializedName("display_name_prefixed")
    @Expose
    private var displayNamePrefixed: String? = null
    @SerializedName("accounts_active")
    @Expose
    private var accountsActive: Any? = null
    @SerializedName("public_traffic")
    @Expose
    private var publicTraffic = false
    @SerializedName("subscribers")
    @Expose
    private var subscribers: Long = 0
    @SerializedName("user_flair_richtext")
    @Expose
    private var userFlairRichtext: List<Any>? = null
    @SerializedName("videostream_links_count")
    @Expose
    private var videostreamLinksCount: Long = 0
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("quarantine")
    @Expose
    private var quarantine = false
    @SerializedName("hide_ads")
    @Expose
    private var hideAds = false
    @SerializedName("emojis_enabled")
    @Expose
    private var emojisEnabled = false
    @SerializedName("advertiser_category")
    @Expose
    private var advertiserCategory: String? = null
    @SerializedName("public_description")
    @Expose
    private var publicDescription: String? = null
    @SerializedName("comment_score_hide_mins")
    @Expose
    private var commentScoreHideMins: Long = 0
    @SerializedName("user_has_favorited")
    @Expose
    private var userHasFavorited: Any? = null
    @SerializedName("user_flair_template_id")
    @Expose
    private var userFlairTemplateId: Any? = null
    @SerializedName("community_icon")
    @Expose
    private var communityIcon: String? = null
    @SerializedName("banner_background_image")
    @Expose
    private var bannerBackgroundImage: String? = null
    @SerializedName("original_content_tag_enabled")
    @Expose
    private var originalContentTagEnabled = false
    @SerializedName("submit_text")
    @Expose
    private var submitText: String? = null
    @SerializedName("description_html")
    @Expose
    private var descriptionHtml: String? = null
    @SerializedName("spoilers_enabled")
    @Expose
    private var spoilersEnabled = false
    @SerializedName("header_title")
    @Expose
    private var headerTitle: String? = null
    @SerializedName("header_size")
    @Expose
    private var headerSize: List<Long>? = null
    @SerializedName("user_flair_position")
    @Expose
    private var userFlairPosition: String? = null
    @SerializedName("all_original_content")
    @Expose
    private var allOriginalContent = false
    @SerializedName("has_menu_widget")
    @Expose
    private var hasMenuWidget = false
    @SerializedName("is_enrolled_in_new_modmail")
    @Expose
    private var isEnrolledInNewModmail: Any? = null
    @SerializedName("key_color")
    @Expose
    private var keyColor: String? = null
    @SerializedName("can_assign_user_flair")
    @Expose
    private var canAssignUserFlair = false
    @SerializedName("created")
    @Expose
    private var created: Long = 0
    @SerializedName("wls")
    @Expose
    private var wls: Long = 0
    @SerializedName("show_media_preview")
    @Expose
    private var showMediaPreview = false
    @SerializedName("submission_type")
    @Expose
    private var submissionType: String? = null
    @SerializedName("user_is_subscriber")
    @Expose
    private var userIsSubscriber: Any? = null
    @SerializedName("disable_contributor_requests")
    @Expose
    private var disableContributorRequests = false
    @SerializedName("allow_videogifs")
    @Expose
    private var allowVideogifs = false
    @SerializedName("user_flair_type")
    @Expose
    private var userFlairType: String? = null
    @SerializedName("allow_polls")
    @Expose
    private var allowPolls = false
    @SerializedName("collapse_deleted_comments")
    @Expose
    private var collapseDeletedComments = false
    @SerializedName("emojis_custom_size")
    @Expose
    private var emojisCustomSize: Any? = null
    @SerializedName("public_description_html")
    @Expose
    private var publicDescriptionHtml: String? = null
    @SerializedName("allow_videos")
    @Expose
    private var allowVideos = false
    @SerializedName("is_crosspostable_subreddit")
    @Expose
    private var isCrosspostableSubreddit = false
    @SerializedName("suggested_comment_sort")
    @Expose
    private var suggestedCommentSort: Any? = null
    @SerializedName("can_assign_link_flair")
    @Expose
    private var canAssignLinkFlair = false
    @SerializedName("accounts_active_is_fuzzed")
    @Expose
    private var accountsActiveIsFuzzed = false
    @SerializedName("submit_text_label")
    @Expose
    private var submitTextLabel: String? = null
    @SerializedName("link_flair_position")
    @Expose
    private var linkFlairPosition: String? = null
    @SerializedName("user_sr_flair_enabled")
    @Expose
    private var userSrFlairEnabled: Any? = null
    @SerializedName("user_flair_enabled_in_sr")
    @Expose
    private var userFlairEnabledInSr = false
    @SerializedName("allow_discovery")
    @Expose
    private var allowDiscovery = false
    @SerializedName("user_sr_theme_enabled")
    @Expose
    private var userSrThemeEnabled = false
    @SerializedName("link_flair_enabled")
    @Expose
    private var linkFlairEnabled = false
    @SerializedName("subreddit_type")
    @Expose
    private var subredditType: String? = null
    @SerializedName("notification_level")
    @Expose
    private var notificationLevel: Any? = null
    @SerializedName("banner_img")
    @Expose
    private var bannerImg: String? = null
    @SerializedName("user_flair_text")
    @Expose
    private var userFlairText: Any? = null
    @SerializedName("banner_background_color")
    @Expose
    private var bannerBackgroundColor: String? = null
    @SerializedName("show_media")
    @Expose
    private var showMedia = false
    @SerializedName("id")
    @Expose
    private var id: String? = null
    @SerializedName("user_is_contributor")
    @Expose
    private var userIsContributor: Any? = null
    @SerializedName("over18")
    @Expose
    private var over18 = false
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("submit_link_label")
    @Expose
    private var submitLinkLabel: String? = null
    @SerializedName("user_flair_text_color")
    @Expose
    private var userFlairTextColor: Any? = null
    @SerializedName("restrict_commenting")
    @Expose
    private var restrictCommenting = false
    @SerializedName("user_flair_css_class")
    @Expose
    private var userFlairCssClass: Any? = null
    @SerializedName("allow_images")
    @Expose
    private var allowImages = false
    @SerializedName("lang")
    @Expose
    private var lang: String? = null
    @SerializedName("whitelist_status")
    @Expose
    private var whitelistStatus: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("created_utc")
    @Expose
    private var createdUtc: Long = 0
    @SerializedName("banner_size")
    @Expose
    private var bannerSize: List<Long>? = null
    @SerializedName("mobile_banner_image")
    @Expose
    private var mobileBannerImage: String? = null
    @SerializedName("user_is_moderator")
    @Expose
    private var userIsModerator: Any? = null
}
