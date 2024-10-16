package com.ben.benhigginsnpr.Util

import com.ben.benhigginsnpr.data.headline.classes.Attributes
import com.ben.benhigginsnpr.data.headline.classes.AttributesX
import com.ben.benhigginsnpr.data.headline.classes.Browse
import com.ben.benhigginsnpr.data.headline.classes.Image
import com.ben.benhigginsnpr.data.headline.classes.Item
import com.ben.benhigginsnpr.data.headline.classes.Links
import com.ben.benhigginsnpr.data.headline.classes.LinksX
import com.ben.benhigginsnpr.data.headline.classes.NPRHeadlineItem
import com.ben.benhigginsnpr.data.headline.classes.Slug
import com.ben.benhigginsnpr.data.headline.classes.Web

class NPRDataUtil {

    companion object {

        val itemEmptyLinks = NPRHeadlineItem(
            attributes = Attributes(
                deepLinkId = "deepLink123",
                id = "id123",
                itemType = "article",
                renderType = "list",
                title = "Top News",
                type = "news"
            ),
            errors = emptyList(), // No errors, hence an empty list
            href = "https://www.npr.org/sample-headline",
            items = listOf(
                Item(
                    attributes = AttributesX(
                        date = "2024-10-15",
                        description = "This is a sample news description.",
                        label = "null",  // Assuming this is nullable
                        provider = "NPR",
                        renderType = "text",
                        slug = Slug(
                            badge = "null", // Nullable badge
                            externalId = "external123",
                            href = "https://www.npr.org/sample-item1",
                            title = "Sample Item 1"
                        ),
                        storyId = "story123",
                        title = "Headline 1",
                        type = "article",
                        uid = "uid123"
                    ),
                    errors = emptyList(),
                    href = "https://www.npr.org/sample-item1",
                    items = emptyList(),  // No nested items
                    links = Links(
                        audio = emptyList(), // No audio links for this example
                        image = emptyList(), // No images for this example
                        profile = emptyList(),
                        program = emptyList(),
                        up = emptyList(),
                        web = emptyList()
                    ),
                    version = "1.0"
                ),
                Item(
                    attributes = AttributesX(
                        date = "2024-10-14",
                        description = "This is the second sample news description.",
                        label = "null",
                        provider = "NPR",
                        renderType = "text",
                        slug = Slug(
                            badge = "null",
                            externalId = "external124",
                            href = "https://www.npr.org/sample-item2",
                            title = "Sample Item 2"
                        ),
                        storyId = "story124",
                        title = "Headline 2",
                        type = "article",
                        uid = "uid124"
                    ),
                    errors = emptyList(),
                    href = "https://www.npr.org/sample-item2",
                    items = emptyList(),
                    links = Links(
                        audio = emptyList(),
                        image = emptyList(),
                        profile = emptyList(),
                        program = emptyList(),
                        up = emptyList(),
                        web = emptyList()
                    ),
                    version = "1.0"
                ),
                Item(
                    attributes = AttributesX(
                        date = "2024-10-13",
                        description = "This is the third sample news description.",
                        label = "null",
                        provider = "NPR",
                        renderType = "text",
                        slug = Slug(
                            badge = "null",
                            externalId = "external125",
                            href = "https://www.npr.org/sample-item3",
                            title = "Sample Item 3"
                        ),
                        storyId = "story125",
                        title = "Headline 3",
                        type = "article",
                        uid = "uid125"
                    ),
                    errors = emptyList(),
                    href = "https://www.npr.org/sample-item3",
                    items = emptyList(),
                    links = Links(
                        audio = emptyList(),
                        image = emptyList(),
                        profile = emptyList(),
                        program = emptyList(),
                        up = emptyList(),
                        web = emptyList()
                    ),
                    version = "1.0"
                )
            ),
            links = LinksX(
                browse = listOf(
                    Browse(
                        contentType = "browseType",
                        href = "https://www.npr.org/browse-link",
                        linkText = "Browse NPR"
                    )
                )
            ),
            version = "1.0"
        )

        val itemFilledLinks = NPRHeadlineItem(
            attributes = Attributes(
                deepLinkId = "deepLink456",
                id = "id456",
                itemType = "news",
                renderType = "carousel",
                title = "Breaking News",
                type = "news"
            ),
            errors = emptyList(), // No errors
            href = "https://www.npr.org/breaking-news",
            items = listOf(
                Item(
                    attributes = AttributesX(
                        date = "2024-10-15",
                        description = "Breaking news item 1 description.",
                        label = "null",  // Assuming this is nullable
                        provider = "NPR",
                        renderType = "image",
                        slug = Slug(
                            badge = "null",
                            externalId = "external126",
                            href = "https://www.npr.org/item1",
                            title = "Breaking News Item 1"
                        ),
                        storyId = "story126",
                        title = "Breaking Headline 1",
                        type = "article",
                        uid = "uid126"
                    ),
                    errors = emptyList(),
                    href = "https://www.npr.org/item1",
                    items = emptyList(),  // No nested items
                    links = Links(
                        audio = emptyList(), // No audio links for this example
                        image = listOf(
                            Image(
                                caption = "Image 1 caption",
                                contentType = "image/jpeg",
                                href = "https://www.npr.org/images/image1.jpg",
                                image = "https://www.npr.org/images/image1.jpg",
                                producer = "Producer 1",
                                provider = "Provider 1",
                                rel = "image"
                            ),
                            Image(
                                caption = "Image 2 caption",
                                contentType = "image/jpeg",
                                href = "https://www.npr.org/images/image2.jpg",
                                image = "https://www.npr.org/images/image2.jpg",
                                producer = "Producer 2",
                                provider = "Provider 2",
                                rel = "image"
                            ),
                            Image(
                                caption = "Image 3 caption",
                                contentType = "image/jpeg",
                                href = "https://www.npr.org/images/image3.jpg",
                                image = "https://www.npr.org/images/image3.jpg",
                                producer = "Producer 3",
                                provider = "Provider 3",
                                rel = "image"
                            )
                        ),
                        profile = emptyList(),
                        program = emptyList(),
                        up = emptyList(),
                        web = listOf(
                            Web(
                                contentType = "text/html",
                                href = "https://www.npr.org/web-link1",
                                linkText = "Web Link 1",
                                rel = "related"
                            ),
                            Web(
                                contentType = "text/html",
                                href = "https://www.npr.org/web-link2",
                                linkText = "Web Link 2",
                                rel = "related"
                            ),
                            Web(
                                contentType = "text/html",
                                href = "https://www.npr.org/web-link3",
                                linkText = "Web Link 3",
                                rel = "related"
                            )
                        )
                    ),
                    version = "1.0"
                ),
                Item(
                    attributes = AttributesX(
                        date = "2024-10-14",
                        description = "Breaking news item 2 description.",
                        label = "null",
                        provider = "NPR",
                        renderType = "image",
                        slug = Slug(
                            badge = "null",
                            externalId = "external127",
                            href = "https://www.npr.org/item2",
                            title = "Breaking News Item 2"
                        ),
                        storyId = "story127",
                        title = "Breaking Headline 2",
                        type = "article",
                        uid = "uid127"
                    ),
                    errors = emptyList(),
                    href = "https://www.npr.org/item2",
                    items = emptyList(),
                    links = Links(
                        audio = emptyList(), // No audio links for this example
                        image = listOf(
                            Image(
                                caption = "Image 1 caption",
                                contentType = "image/jpeg",
                                href = "https://www.npr.org/images/image1.jpg",
                                image = "https://www.npr.org/images/image1.jpg",
                                producer = "Producer 1",
                                provider = "Provider 1",
                                rel = "image"
                            ),
                            Image(
                                caption = "Image 2 caption",
                                contentType = "image/jpeg",
                                href = "https://www.npr.org/images/image2.jpg",
                                image = "https://www.npr.org/images/image2.jpg",
                                producer = "Producer 2",
                                provider = "Provider 2",
                                rel = "image"
                            ),
                            Image(
                                caption = "Image 3 caption",
                                contentType = "image/jpeg",
                                href = "https://www.npr.org/images/image3.jpg",
                                image = "https://www.npr.org/images/image3.jpg",
                                producer = "Producer 3",
                                provider = "Provider 3",
                                rel = "image"
                            )
                        ),
                        profile = emptyList(),
                        program = emptyList(),
                        up = emptyList(),
                        web = listOf(
                            Web(
                                contentType = "text/html",
                                href = "https://www.npr.org/web-link1",
                                linkText = "Web Link 1",
                                rel = "related"
                            ),
                            Web(
                                contentType = "text/html",
                                href = "https://www.npr.org/web-link2",
                                linkText = "Web Link 2",
                                rel = "related"
                            ),
                            Web(
                                contentType = "text/html",
                                href = "https://www.npr.org/web-link3",
                                linkText = "Web Link 3",
                                rel = "related"
                            )
                        )
                    ),
                    version = "1.0"
                ),
                Item(
                    attributes = AttributesX(
                        date = "2024-10-14",
                        description = "Breaking news item 2 description.",
                        label = "null",
                        provider = "NPR",
                        renderType = "image",
                        slug = Slug(
                            badge = "null",
                            externalId = "external127",
                            href = "https://www.npr.org/item2",
                            title = "Breaking News Item 2"
                        ),
                        storyId = "story127",
                        title = "Breaking Headline 3",
                        type = "article",
                        uid = "uid127"
                    ),
                    errors = emptyList(),
                    href = "https://www.npr.org/item2",
                    items = emptyList(),
                    links = Links(
                        audio = emptyList(), // No audio links for this example
                        image = listOf(
                            Image(
                                caption = "Image 1 caption",
                                contentType = "image/jpeg",
                                href = "https://www.npr.org/images/image1.jpg",
                                image = "https://www.npr.org/images/image1.jpg",
                                producer = "Producer 1",
                                provider = "Provider 1",
                                rel = "image"
                            ),
                            Image(
                                caption = "Image 2 caption",
                                contentType = "image/jpeg",
                                href = "https://www.npr.org/images/image2.jpg",
                                image = "https://www.npr.org/images/image2.jpg",
                                producer = "Producer 2",
                                provider = "Provider 2",
                                rel = "image"
                            ),
                            Image(
                                caption = "Image 3 caption",
                                contentType = "image/jpeg",
                                href = "https://www.npr.org/images/image3.jpg",
                                image = "https://www.npr.org/images/image3.jpg",
                                producer = "Producer 3",
                                provider = "Provider 3",
                                rel = "image"
                            )
                        ),
                        profile = emptyList(),
                        program = emptyList(),
                        up = emptyList(),
                        web = listOf(
                            Web(
                                contentType = "text/html",
                                href = "https://www.npr.org/web-link1",
                                linkText = "Web Link 1",
                                rel = "related"
                            ),
                            Web(
                                contentType = "text/html",
                                href = "https://www.npr.org/web-link2",
                                linkText = "Web Link 2",
                                rel = "related"
                            ),
                            Web(
                                contentType = "text/html",
                                href = "https://www.npr.org/web-link3",
                                linkText = "Web Link 3",
                                rel = "related"
                            )
                        )
                    ),
                    version = "1.0"
                )

            ),
            links = LinksX(
                browse = listOf(
                    Browse(
                        contentType = "browseType",
                        href = "https://www.npr.org/browse-breaking-news",
                        linkText = "Browse Breaking News"
                    )
                )
            ),
            version = "1.0"
        )

    }

}