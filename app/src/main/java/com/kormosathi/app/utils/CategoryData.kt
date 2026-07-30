package com.kormosathi.app.utils

import com.kormosathi.app.model.Category

object CategoryData {

    val categories = listOf(

        Category(
            id = "home_services",
            nameEn = "Home Services",
            nameBn = "হোম সার্ভিস",
            icon = "home",
            imageUrl = "",
            order = 1
        ),

        Category(
            id = "repair_installation",
            nameEn = "Repair & Installation",
            nameBn = "রিপেয়ার ও ইনস্টলেশন",
            icon = "build",
            imageUrl = "",
            order = 2
        ),

        Category(
            id = "cleaning",
            nameEn = "Cleaning Services",
            nameBn = "পরিষ্কার-পরিচ্ছন্নতা",
            icon = "cleaning_services",
            imageUrl = "",
            order = 3
        ),

        Category(
            id = "vehicle",
            nameEn = "Vehicle Services",
            nameBn = "যানবাহন সার্ভিস",
            icon = "directions_car",
            imageUrl = "",
            order = 4
        ),

        Category(
            id = "personal",
            nameEn = "Personal Services",
            nameBn = "ব্যক্তিগত সার্ভিস",
            icon = "person",
            imageUrl = "",
            order = 5
        ),

        Category(
            id = "education",
            nameEn = "Education",
            nameBn = "শিক্ষা",
            icon = "school",
            imageUrl = "",
            order = 6
        ),

        Category(
            id = "events",
            nameEn = "Events",
            nameBn = "ইভেন্ট",
            icon = "celebration",
            imageUrl = "",
            order = 7
        ),

        Category(
            id = "business",
            nameEn = "Business & Digital",
            nameBn = "ব্যবসা ও ডিজিটাল",
            icon = "business",
            imageUrl = "",
            order = 8
        ),

        Category(
            id = "health",
            nameEn = "Health",
            nameBn = "স্বাস্থ্য",
            icon = "health_and_safety",
            imageUrl = "",
            order = 9
        ),

        Category(
            id = "travel",
            nameEn = "Travel",
            nameBn = "ভ্রমণ",
            icon = "travel_explore",
            imageUrl = "",
            order = 10
        ),

        Category(
            id = "outdoor",
            nameEn = "Outdoor Services",
            nameBn = "বাহিরের সার্ভিস",
            icon = "park",
            imageUrl = "",
            order = 11
        )
    )

    val subCategories = mapOf(

        "home_services" to listOf(
            "Electrician",
            "Plumber",
            "Carpenter",
            "Painter",
            "Mason",
            "Tiles Work",
            "False Ceiling"
        ),

        "repair_installation" to listOf(
            "AC Repair",
            "AC Installation",
            "RO Repair",
            "Computer Repair",
            "Laptop Repair",
            "CCTV Installation",
            "Appliance Repair"
        ),

        "cleaning" to listOf(
            "Home Cleaning",
            "Office Cleaning",
            "Bathroom Cleaning",
            "Kitchen Cleaning",
            "Sofa Cleaning",
            "Water Tank Cleaning"
        ),

        "vehicle" to listOf(
            "Bike Repair",
            "Car Repair",
            "Car Washing",
            "Bike Washing",
            "Tyre Service",
            "Battery Service"
        ),

        "personal" to listOf(
            "Beauty Service",
            "Salon Service",
            "Makeup Artist",
            "Massage Service",
            "Personal Trainer"
        ),

        "education" to listOf(
            "Home Tutor",
            "Computer Training",
            "Spoken English",
            "Music Teacher",
            "Dance Teacher"
        ),

        "events" to listOf(
            "Photographer",
            "Videographer",
            "Event Decoration",
            "Catering",
            "DJ Service",
            "Event Planning"
        ),

        "business" to listOf(
            "Graphic Design",
            "Web Development",
            "Digital Marketing",
            "Social Media Management",
            "Printing Service",
            "Logo Design"
        ),

        "health" to listOf(
            "Home Nursing",
            "Physiotherapy",
            "Doctor Consultation",
            "Medical Assistance"
        ),

        "travel" to listOf(
            "Car Rental",
            "Taxi Service",
            "Tour Guide",
            "Travel Booking"
        ),

        "outdoor" to listOf(
            "Gardening",
            "Pest Control",
            "Security Service",
            "Outdoor Cleaning"
        )
    )

    val services = mapOf(

        "Electrician" to listOf(
            "Fan Installation",
            "Switch Repair",
            "Light Installation",
            "House Wiring",
            "MCB Repair",
            "Inverter Wiring"
        ),

        "Plumber" to listOf(
            "Tap Repair",
            "Pipe Repair",
            "Bathroom Fitting",
            "Water Tank Installation",
            "Drainage Repair"
        ),

        "Carpenter" to listOf(
            "Door Repair",
            "Window Repair",
            "Furniture Repair",
            "Wooden Furniture Making",
            "Modular Furniture"
        ),

        "Painter" to listOf(
            "House Painting",
            "Interior Painting",
            "Exterior Painting",
            "Wall Texture",
            "Waterproof Painting"
        ),

        "Mason" to listOf(
            "Brick Work",
            "Cement Work",
            "Wall Construction",
            "Plaster Work",
            "Concrete Work"
        ),

        "Tiles Work" to listOf(
            "Floor Tiles Installation",
            "Wall Tiles Installation",
            "Bathroom Tiles Work",
            "Kitchen Tiles Work",
            "Tiles Repair"
        ),

        "False Ceiling" to listOf(
            "POP Ceiling",
            "Gypsum Ceiling",
            "PVC Ceiling",
            "False Ceiling Repair"
        ),

        "AC Repair" to listOf(
            "AC Repair",
            "AC Service",
            "AC Gas Charging",
            "AC Installation",
            "AC Uninstallation"
        ),

        "AC Installation" to listOf(
            "Split AC Installation",
            "Window AC Installation",
            "AC Uninstallation",
            "AC Relocation"
        ),

        "RO Repair" to listOf(
            "RO Service",
            "RO Filter Change",
            "RO Installation",
            "RO Repair",
            "Water Purifier Service"
        ),

        "Computer Repair" to listOf(
            "Desktop Repair",
            "Computer Formatting",
            "Windows Installation",
            "Hardware Repair",
            "Software Installation"
        ),

        "Laptop Repair" to listOf(
            "Laptop Screen Repair",
            "Laptop Keyboard Repair",
            "Laptop Battery Replacement",
            "Laptop Formatting",
            "Laptop Hardware Repair"
        ),

        "CCTV Installation" to listOf(
            "CCTV Camera Installation",
            "CCTV Repair",
            "CCTV Maintenance",
            "DVR Setup",
            "Remote CCTV Setup"
        ),

        "Appliance Repair" to listOf(
            "Refrigerator Repair",
            "Washing Machine Repair",
            "Microwave Repair",
            "TV Repair",
            "Mixer Grinder Repair"
        ),

        "Home Cleaning" to listOf(
            "Full Home Cleaning",
            "Deep Cleaning",
            "Move-In Cleaning",
            "Move-Out Cleaning"
        ),

        "Office Cleaning" to listOf(
            "Office Deep Cleaning",
            "Daily Office Cleaning",
            "Commercial Cleaning"
        ),

        "Bathroom Cleaning" to listOf(
            "Bathroom Deep Cleaning",
            "Bathroom Sanitisation",
            "Tile Cleaning"
        ),

        "Kitchen Cleaning" to listOf(
            "Kitchen Deep Cleaning",
            "Chimney Cleaning",
            "Kitchen Platform Cleaning"
        ),

        "Sofa Cleaning" to listOf(
            "Fabric Sofa Cleaning",
            "Leather Sofa Cleaning",
            "Chair Cleaning"
        ),

        "Water Tank Cleaning" to listOf(
            "Overhead Tank Cleaning",
            "Underground Tank Cleaning",
            "Water Tank Sanitisation"
        ),

        "Bike Repair" to listOf(
            "Bike General Service",
            "Bike Engine Repair",
            "Bike Brake Repair",
            "Bike Electrical Repair"
        ),

        "Car Repair" to listOf(
            "Car General Service",
            "Car Engine Repair",
            "Car Brake Repair",
            "Car Electrical Repair"
        ),

        "Car Washing" to listOf(
            "Exterior Car Wash",
            "Interior Car Cleaning",
            "Car Polishing",
            "Car Detailing"
        ),

        "Bike Washing" to listOf(
            "Bike Wash",
            "Bike Polishing",
            "Bike Detailing"
        ),

        "Tyre Service" to listOf(
            "Tyre Repair",
            "Tyre Replacement",
            "Wheel Balancing",
            "Wheel Alignment"
        ),

        "Battery Service" to listOf(
            "Battery Replacement",
            "Battery Charging",
            "Battery Checkup"
        ),

        "Beauty Service" to listOf(
            "Bridal Makeup",
            "Party Makeup",
            "Facial",
            "Hair Styling",
            "Beauty Package"
        ),

        "Salon Service" to listOf(
            "Hair Cutting",
            "Hair Styling",
            "Hair Colour",
            "Shaving",
            "Grooming"
        ),

        "Makeup Artist" to listOf(
            "Bridal Makeup",
            "Reception Makeup",
            "Party Makeup",
            "Engagement Makeup"
        ),

        "Massage Service" to listOf(
            "Body Massage",
            "Head Massage",
            "Foot Massage",
            "Home Massage Service"
        ),

        "Personal Trainer" to listOf(
            "Home Fitness Training",
            "Weight Loss Training",
            "Muscle Building Training",
            "Yoga Training"
        ),

        "Home Tutor" to listOf(
            "Class 1 to 5 Tuition",
            "Class 6 to 10 Tuition",
            "Class 11 to 12 Tuition",
            "Competitive Exam Coaching"
        ),

        "Computer Training" to listOf(
            "Basic Computer Training",
            "MS Office Training",
            "Graphic Design Training",
            "Programming Training"
        ),

        "Spoken English" to listOf(
            "Basic Spoken English",
            "Advanced Spoken English",
            "Interview English"
        ),

        "Music Teacher" to listOf(
            "Vocal Music",
            "Keyboard Training",
            "Guitar Training",
            "Tabla Training"
        ),

        "Dance Teacher" to listOf(
            "Classical Dance",
            "Modern Dance",
            "Folk Dance",
            "Wedding Dance"
        ),

        "Photographer" to listOf(
            "Wedding Photography",
            "Birthday Photography",
            "Event Photography",
            "Product Photography",
            "Portrait Photography"
        ),

        "Videographer" to listOf(
            "Wedding Video",
            "Event Video",
            "Promotional Video",
            "Reel Video",
            "YouTube Video"
        ),

        "Event Decoration" to listOf(
            "Wedding Decoration",
            "Birthday Decoration",
            "Stage Decoration",
            "Flower Decoration"
        ),

        "Catering" to listOf(
            "Wedding Catering",
            "Birthday Catering",
            "Corporate Catering",
            "Home Party Catering"
        ),

        "DJ Service" to listOf(
            "Wedding DJ",
            "Birthday DJ",
            "Party DJ",
            "Sound System Rental"
        ),

        "Event Planning" to listOf(
            "Wedding Planning",
            "Birthday Planning",
            "Corporate Event Planning",
            "Festival Event Planning"
        ),

        "Graphic Design" to listOf(
            "Logo Design",
            "Banner Design",
            "Visiting Card Design",
            "Social Media Post Design",
            "Flex Design",
            "Wedding Card Design",
            "Poster Design"
        ),

        "Web Development" to listOf(
            "Business Website",
            "Portfolio Website",
            "E-Commerce Website",
            "Landing Page Design"
        ),

        "Digital Marketing" to listOf(
            "Facebook Marketing",
            "Instagram Marketing",
            "Google Ads",
            "SEO Service"
        ),

        "Social Media Management" to listOf(
            "Facebook Page Management",
            "Instagram Management",
            "Content Planning",
            "Social Media Advertising"
        ),

        "Printing Service" to listOf(
            "Flex Printing",
            "Banner Printing",
            "Visiting Card Printing",
            "Sticker Printing",
            "Offset Printing"
        ),

        "Logo Design" to listOf(
            "Business Logo",
            "Company Logo",
            "3D Logo",
            "Minimal Logo Design"
        ),

        "Home Nursing" to listOf(
            "Elderly Care",
            "Patient Care",
            "Post-Operation Care",
            "Home Nursing Support"
        ),

        "Physiotherapy" to listOf(
            "Home Physiotherapy",
            "Back Pain Therapy",
            "Joint Pain Therapy",
            "Sports Injury Therapy"
        ),

        "Doctor Consultation" to listOf(
            "General Doctor Consultation",
            "Online Doctor Consultation",
            "Home Doctor Visit"
        ),

        "Medical Assistance" to listOf(
            "Medicine Delivery",
            "Medical Attendant",
            "Hospital Assistance"
        ),

        "Car Rental" to listOf(
            "Local Car Rental",
            "Outstation Car Rental",
            "Wedding Car Rental"
        ),

        "Taxi Service" to listOf(
            "Local Taxi",
            "Airport Taxi",
            "Outstation Taxi"
        ),

        "Tour Guide" to listOf(
            "Local Tour Guide",
            "Historical Tour Guide",
            "Custom Travel Guide"
        ),

        "Travel Booking" to listOf(
            "Hotel Booking",
            "Train Ticket Assistance",
            "Flight Booking Assistance",
            "Tour Package Booking"
        ),

        "Gardening" to listOf(
            "Garden Maintenance",
            "Planting Service",
            "Lawn Care",
            "Garden Design"
        ),

        "Pest Control" to listOf(
            "Termite Control",
            "Cockroach Control",
            "Mosquito Control",
            "Rodent Control"
        ),

        "Security Service" to listOf(
            "Home Security Guard",
            "Event Security",
            "Office Security"
        ),

        "Outdoor Cleaning" to listOf(
            "Garden Cleaning",
            "Compound Cleaning",
            "Drain Cleaning"
        )
    )

    fun getSubCategories(
        categoryId: String
    ): List<String> {

        return subCategories[categoryId]
            ?: emptyList()
    }

    fun getServices(
        subCategory: String
    ): List<String> {

        return services[subCategory]
            ?: emptyList()
    }
}