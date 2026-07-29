package com.kormosathi.app.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kormosathi.app.ui.navigation.Screen

@Composable
fun ServiceItemSelectionScreen(
    navController: NavHostController,
    subCategory: String
) {

    val services = when (subCategory) {

        "Electrician" -> listOf(
            "Fan Installation",
            "Switch Repair",
            "Light Installation",
            "House Wiring",
            "MCB Repair",
            "Inverter Wiring"
        )

        "Plumber" -> listOf(
            "Tap Repair",
            "Pipe Repair",
            "Bathroom Fitting",
            "Water Tank Installation",
            "Drainage Repair"
        )

        "Carpenter" -> listOf(
            "Door Repair",
            "Window Repair",
            "Furniture Repair",
            "Wooden Furniture Making",
            "Modular Furniture"
        )

        "Painter" -> listOf(
            "House Painting",
            "Interior Painting",
            "Exterior Painting",
            "Wall Texture",
            "Waterproof Painting"
        )

        "Mason" -> listOf(
            "Brick Work",
            "Cement Work",
            "Wall Construction",
            "Plaster Work",
            "Concrete Work"
        )

        "Tiles Work" -> listOf(
            "Floor Tiles Installation",
            "Wall Tiles Installation",
            "Bathroom Tiles Work",
            "Kitchen Tiles Work",
            "Tiles Repair"
        )

        "False Ceiling" -> listOf(
            "POP Ceiling",
            "Gypsum Ceiling",
            "PVC Ceiling",
            "False Ceiling Repair"
        )

        "AC Repair" -> listOf(
            "AC Repair",
            "AC Service",
            "AC Gas Charging",
            "AC Installation",
            "AC Uninstallation"
        )

        "AC Installation" -> listOf(
            "Split AC Installation",
            "Window AC Installation",
            "AC Uninstallation",
            "AC Relocation"
        )

        "RO Repair" -> listOf(
            "RO Service",
            "RO Filter Change",
            "RO Installation",
            "RO Repair",
            "Water Purifier Service"
        )

        "Computer Repair" -> listOf(
            "Desktop Repair",
            "Computer Formatting",
            "Windows Installation",
            "Hardware Repair",
            "Software Installation"
        )

        "Laptop Repair" -> listOf(
            "Laptop Screen Repair",
            "Laptop Keyboard Repair",
            "Laptop Battery Replacement",
            "Laptop Formatting",
            "Laptop Hardware Repair"
        )

        "CCTV Installation" -> listOf(
            "CCTV Camera Installation",
            "CCTV Repair",
            "CCTV Maintenance",
            "DVR Setup",
            "Remote CCTV Setup"
        )

        "Appliance Repair" -> listOf(
            "Refrigerator Repair",
            "Washing Machine Repair",
            "Microwave Repair",
            "TV Repair",
            "Mixer Grinder Repair"
        )

        "Home Cleaning" -> listOf(
            "Full Home Cleaning",
            "Deep Cleaning",
            "Move-In Cleaning",
            "Move-Out Cleaning"
        )

        "Office Cleaning" -> listOf(
            "Office Deep Cleaning",
            "Daily Office Cleaning",
            "Commercial Cleaning"
        )

        "Bathroom Cleaning" -> listOf(
            "Bathroom Deep Cleaning",
            "Bathroom Sanitisation",
            "Tile Cleaning"
        )

        "Kitchen Cleaning" -> listOf(
            "Kitchen Deep Cleaning",
            "Chimney Cleaning",
            "Kitchen Platform Cleaning"
        )

        "Sofa Cleaning" -> listOf(
            "Fabric Sofa Cleaning",
            "Leather Sofa Cleaning",
            "Chair Cleaning"
        )

        "Water Tank Cleaning" -> listOf(
            "Overhead Tank Cleaning",
            "Underground Tank Cleaning",
            "Water Tank Sanitisation"
        )

        "Bike Repair" -> listOf(
            "Bike General Service",
            "Bike Engine Repair",
            "Bike Brake Repair",
            "Bike Electrical Repair"
        )

        "Car Repair" -> listOf(
            "Car General Service",
            "Car Engine Repair",
            "Car Brake Repair",
            "Car Electrical Repair"
        )

        "Car Washing" -> listOf(
            "Exterior Car Wash",
            "Interior Car Cleaning",
            "Car Polishing",
            "Car Detailing"
        )

        "Bike Washing" -> listOf(
            "Bike Wash",
            "Bike Polishing",
            "Bike Detailing"
        )

        "Tyre Service" -> listOf(
            "Tyre Repair",
            "Tyre Replacement",
            "Wheel Balancing",
            "Wheel Alignment"
        )

        "Battery Service" -> listOf(
            "Battery Replacement",
            "Battery Charging",
            "Battery Checkup"
        )

        "Beauty Service" -> listOf(
            "Bridal Makeup",
            "Party Makeup",
            "Facial",
            "Hair Styling",
            "Beauty Package"
        )

        "Salon Service" -> listOf(
            "Hair Cutting",
            "Hair Styling",
            "Hair Colour",
            "Shaving",
            "Grooming"
        )

        "Makeup Artist" -> listOf(
            "Bridal Makeup",
            "Reception Makeup",
            "Party Makeup",
            "Engagement Makeup"
        )

        "Massage Service" -> listOf(
            "Body Massage",
            "Head Massage",
            "Foot Massage",
            "Home Massage Service"
        )

        "Personal Trainer" -> listOf(
            "Home Fitness Training",
            "Weight Loss Training",
            "Muscle Building Training",
            "Yoga Training"
        )

        "Home Tutor" -> listOf(
            "Class 1 to 5 Tuition",
            "Class 6 to 10 Tuition",
            "Class 11 to 12 Tuition",
            "Competitive Exam Coaching"
        )

        "Computer Training" -> listOf(
            "Basic Computer Training",
            "MS Office Training",
            "Graphic Design Training",
            "Programming Training"
        )

        "Spoken English" -> listOf(
            "Basic Spoken English",
            "Advanced Spoken English",
            "Interview English"
        )

        "Music Teacher" -> listOf(
            "Vocal Music",
            "Keyboard Training",
            "Guitar Training",
            "Tabla Training"
        )

        "Dance Teacher" -> listOf(
            "Classical Dance",
            "Modern Dance",
            "Folk Dance",
            "Wedding Dance"
        )

        "Photographer" -> listOf(
            "Wedding Photography",
            "Birthday Photography",
            "Event Photography",
            "Product Photography",
            "Portrait Photography"
        )

        "Videographer" -> listOf(
            "Wedding Video",
            "Event Video",
            "Promotional Video",
            "Reel Video",
            "YouTube Video"
        )

        "Event Decoration" -> listOf(
            "Wedding Decoration",
            "Birthday Decoration",
            "Stage Decoration",
            "Flower Decoration"
        )

        "Catering" -> listOf(
            "Wedding Catering",
            "Birthday Catering",
            "Corporate Catering",
            "Home Party Catering"
        )

        "DJ Service" -> listOf(
            "Wedding DJ",
            "Birthday DJ",
            "Party DJ",
            "Sound System Rental"
        )

        "Event Planning" -> listOf(
            "Wedding Planning",
            "Birthday Planning",
            "Corporate Event Planning",
            "Festival Event Planning"
        )

        "Graphic Design" -> listOf(
            "Logo Design",
            "Banner Design",
            "Visiting Card Design",
            "Social Media Post Design",
            "Flex Design",
            "Wedding Card Design",
            "Poster Design"
        )

        "Web Development" -> listOf(
            "Business Website",
            "Portfolio Website",
            "E-Commerce Website",
            "Landing Page Design"
        )

        "Digital Marketing" -> listOf(
            "Facebook Marketing",
            "Instagram Marketing",
            "Google Ads",
            "SEO Service"
        )

        "Social Media Management" -> listOf(
            "Facebook Page Management",
            "Instagram Management",
            "Content Planning",
            "Social Media Advertising"
        )

        "Printing Service" -> listOf(
            "Flex Printing",
            "Banner Printing",
            "Visiting Card Printing",
            "Sticker Printing",
            "Offset Printing"
        )

        "Logo Design" -> listOf(
            "Business Logo",
            "Company Logo",
            "3D Logo",
            "Minimal Logo Design"
        )

        "Home Nursing" -> listOf(
            "Elderly Care",
            "Patient Care",
            "Post-Operation Care",
            "Home Nursing Support"
        )

        "Physiotherapy" -> listOf(
            "Home Physiotherapy",
            "Back Pain Therapy",
            "Joint Pain Therapy",
            "Sports Injury Therapy"
        )

        "Doctor Consultation" -> listOf(
            "General Doctor Consultation",
            "Online Doctor Consultation",
            "Home Doctor Visit"
        )

        "Medical Assistance" -> listOf(
            "Medicine Delivery",
            "Medical Attendant",
            "Hospital Assistance"
        )

        "Car Rental" -> listOf(
            "Local Car Rental",
            "Outstation Car Rental",
            "Wedding Car Rental"
        )

        "Taxi Service" -> listOf(
            "Local Taxi",
            "Airport Taxi",
            "Outstation Taxi"
        )

        "Tour Guide" -> listOf(
            "Local Tour Guide",
            "Historical Tour Guide",
            "Custom Travel Guide"
        )

        "Travel Booking" -> listOf(
            "Hotel Booking",
            "Train Ticket Assistance",
            "Flight Booking Assistance",
            "Tour Package Booking"
        )

        "Gardening" -> listOf(
            "Garden Maintenance",
            "Planting Service",
            "Lawn Care",
            "Garden Design"
        )

        "Pest Control" -> listOf(
            "Termite Control",
            "Cockroach Control",
            "Mosquito Control",
            "Rodent Control"
        )

        "Security Service" -> listOf(
            "Home Security Guard",
            "Event Security",
            "Office Security"
        )

        "Outdoor Cleaning" -> listOf(
            "Garden Cleaning",
            "Compound Cleaning",
            "Drain Cleaning"
        )

        else -> listOf(
            "Service Available Soon"
        )

    }

    var selectedService by remember(subCategory) {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "সার্ভিস নির্বাচন করুন",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "নির্বাচিত সাব-ক্যাটাগরি: $subCategory",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {

            items(
                items = services,
                key = { service -> service }
            ) { service ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            if (
                                service !=
                                "Service Available Soon"
                            ) {
                                selectedService = service
                            }
                        }
                        .padding(
                            vertical = 12.dp
                        ),

                    verticalAlignment =
                        Alignment.CenterVertically,

                    horizontalArrangement =
                        Arrangement.SpaceBetween
                ) {

                    Text(
                        text = service,
                        style =
                            MaterialTheme
                                .typography
                                .titleMedium
                    )

                    RadioButton(
                        selected =
                            selectedService == service,

                        enabled =
                            service !=
                                    "Service Available Soon",

                        onClick = {

                            if (
                                service !=
                                "Service Available Soon"
                            ) {
                                selectedService = service
                            }

                        }
                    )

                }

                HorizontalDivider()

            }

        }

        Button(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(52.dp),

            enabled =
                selectedService.isNotBlank(),

            onClick = {

                navController.navigate(
                    Screen.ProviderDashboard.route
                ) {

                    popUpTo(
                        Screen.Profile.route
                    ) {
                        inclusive = false
                    }

                }

            }

        ) {

            Text(
                text = "Finish"
            )

        }

    }

}