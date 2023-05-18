package com.example.donation.data

import com.example.donation.model.Faqs

class FaqsSource {

    fun loadFaqsData() : List<Faqs> {
        return listOf(
            Faqs(
                "Why does Wildcare use fundraising agencies to fundraise?",
                "As much as we would like to use only our own staffs, we are unable to bear the extensive cost of setting up a team large enough to accommodate and run this programme. By partnering with fundraising agencies,  Wildcare-Malaysia does not only save costs but also allow us to channel more of your valuable donations directly to our conservation programmes."
            ),
            Faqs(
                "What happens to the funds raised?",
                "The funds raised are used by Wildcare to carry out scientific fieldwork, environmental education, and policy work with the government and community programmes that aid nature conservation."
            ),Faqs(
                "Why doesn’t Wildcare use volunteers?",
                "Face-to-face fundraising is a regular, professional job that requires long-term commitment, hard work and specific skills. All Wildcare representatives undergo intensive training by Wildcare and the fundraising agencies, before they begin work. While we sincerely acknowledge our volunteers for playing a valuable role in certain fundraising activities, their commitments outside Wildcare is beyond our control thus making them not ideal for this particular type of programme."
            ),
            Faqs(
                "Is my donation tax exempted?",
                "Yes, Wildcare-Malaysia is an NGO under sub-section 44 (6) of Income Tax Act 1967, and all cash donations to Wildcare-Malaysia are tax deductible (applicable only to donations made within Malaysia)."
            ),
            Faqs(
                "Is there a minimum amount of donation required for me to be eligible for tax-exemption?",
                " No. You are free to donate as much or as little an amount. However, please note that we have a certain level of administrative cost that needs to be maintained (postage, notification systems etc), as such we require at least RM10 should you wish to claim for tax-exemption.\n" +
                        "\n" +
                        "For donations of RM20,000 and above in a single receipt, we are required to declare the donation to the Inland Revenue Board of Malaysia."
            ),
            Faqs(
                "What information do you need from me if I want to get a tax-exemption receipt for donation?",
                "The Inland Revenue Board (IRB, or commonly known as LHDN, Lembaga Hasil Dalam Negeri) has recently issued the revised Guidelines for Approval of Director General of Inland Revenue under Subsection 44(6) of the Income Tax Act dated 15 May 2019 (“Revised Guidelines\"). The Revised Guidelines supersede the previous Guidelines dated April 2005 (“Previous Guidelines”).\n" +
                        "\n" +
                        "Based on the Revised Guidelines, donors must provide complete information of the following details in order to obtain an official tax-exemption receipt from the organisation:\n" +
                        "Donor’s full name as per NRIC / Passport\n" +
                        "NRIC number / passport number\n" +
                        "Complete mailing address of current residence\n" +
                        "The Revised Guidelines also states that the approved institution or organisation is not allowed to issue tax-exemption receipts to donors who fail to provide their complete personal details stated above. "
            )
        )
    }
}