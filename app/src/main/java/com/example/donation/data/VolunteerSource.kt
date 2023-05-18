package com.example.donation.data

import com.example.donation.Campaign.Volunteer.VolunteerViewModel
import com.example.donation.R
import com.example.donation.model.Volunteer
import com.example.donation.model.VolunteerRole

class VolunteerSource {
    val volunteerRoles = listOf(
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
                "Join us on the upcoming earth hour event",
                "31 September 2023",
                "8:00 am",
                "Kuala Lumpur",
                listOf(volunteerRoles[0], volunteerRoles[3])
            ),
            Volunteer(
                R.drawable.image3,
                "Let's Save the Planet Together!",
                "15 October 2023",
                "10:00 am",
                "Selangor",
                listOf(volunteerRoles[1], volunteerRoles[3])
            ),
            Volunteer(
                R.drawable.image2,
                "Creating a Cleaner Environment Together",
                "17 October 2023",
                "9:00 am",
                "Redang Island",
                listOf(volunteerRoles[2], volunteerRoles[1])
            ),
            Volunteer(
                R.drawable.image1,
                "Clean the Zoo",
                "31 November 2023",
                "8:00 am",
                "Kuala Lumpur",
                listOf(volunteerRoles[3], volunteerRoles[2])
            )
        )
    }
}