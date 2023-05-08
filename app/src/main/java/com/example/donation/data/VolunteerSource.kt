package com.example.donation.data

import com.example.donation.R
import com.example.donation.model.Volunteer
import com.example.donation.model.VolunteerRole

class VolunteerSource {
    private val volunteerRoles = listOf(
        VolunteerRole(
            "Social Media Coordinator",
            jobScopes = listOf(
                "Develop and implement marketing campaigns",
                "Create social media content and manage accounts",
                "Collaborate with other departments to promote events and initiatives"
            ),
            skills = listOf(
                "Excellent written and verbal communication skills",
                "Ability to use Adobe Creative Suite or other design software",
                "Experience with social media management tools such as Hootsuite or Buffer"
            )
        ),
        VolunteerRole(
            "Event Coordinator",
            jobScopes = listOf(
                "Plan and organize events",
                "Manage vendors and volunteers",
                "Ensure events run smoothly and meet objectives"
            ),
            skills = listOf(
                "Strong organizational and project management skills",
                "Excellent interpersonal and communication skills",
                "Ability to problem-solve and think creatively"
            )
        ),
        VolunteerRole(
            "Volunteer Coordinator",
            jobScopes = listOf(
                "Recruit and manage volunteers",
                "Create social media content",
                "Engage with followers"
            ),
            skills = listOf(
                "Interpersonal skills",
                "Management skills",
                "Social media marketing skills"
            )
        ),
        VolunteerRole(
            "Fundraising Coordinator",
            jobScopes = listOf(
                "Develop fundraising strategies",
                "Solicit donations",
                "Coordinate sponsorship packages"
            ),
            skills = listOf(
                "Fundraising experience",
                "Negotiation skills",
                "Sales skills"
            )
        )
    )

    fun eventDescriptionList(): List<Volunteer> {
        return listOf(
            Volunteer(
                R.drawable.image4,
                "Join us on the incoming earth hour event",
                "31 March 2023",
                "8:00 am",
                "Puchong",
                listOf(volunteerRoles[0], volunteerRoles[3])
            ),
            Volunteer(
                R.drawable.image3,
                "Join us on the incoming earth hour event",
                "31 March 2023",
                "8:00 am",
                "Puchong",
                listOf(volunteerRoles[1], volunteerRoles[3])
            ),
            Volunteer(
                R.drawable.image2,
                "Join us on the incoming earth hour event",
                "31 March 2023",
                "8:00 am",
                "Puchong",
                listOf(volunteerRoles[2], volunteerRoles[1])
            ),
            Volunteer(
                R.drawable.image1,
                "Join us on the incoming earth hour event",
                "31 March 2023",
                "8:00 am",
                "Puchong",
                listOf(volunteerRoles[3], volunteerRoles[2])
            )
        )
    }
}