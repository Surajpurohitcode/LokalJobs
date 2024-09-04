# Android Intern Assignment - Job Listings App

This Android application was developed as part of an internship assignment. The app allows users to browse job listings, view detailed information about each job, and bookmark their favorite jobs for offline viewing.

## Features

- **Bottom Navigation UI**: The app includes a bottom navigation bar with two sections:
  - **Jobs**: Displays a list of job postings fetched from the provided API endpoint using a paginated approach.
  - **Bookmarks**: Displays all the jobs that have been bookmarked by the user for offline access.

- **Job Listings Screen**:
  - Fetches job data from the API: [https://testapi.getlokalapp.com/common/jobs?page=1](https://testapi.getlokalapp.com/common/jobs?page=1).
  - Displays job title, location, salary, and contact information in a card view format.
  - Supports pagination for loading jobs dynamically as the user scrolls.

- **Job Details Screen**:
  - Clicking on a job card shows more details related to that job on a new screen.

- **Bookmark Functionality**:
  - Users can bookmark any job by clicking a button on the job details screen.
  - All bookmarked jobs are stored in an SQLite database using Room for offline viewing.

- **Bookmarks Screen**:
  - Displays all bookmarked jobs stored locally in the app's database.
  - Allows users to view their saved jobs even when offline.

- **Loading, Error, and Empty States**:
  - Proper handling of loading, error, and empty states to ensure a smooth user experience.

- **Best Practices and Clean Architecture**:
  - Implements MVVM architecture for better code organization and maintainability.
  - Utilizes Android Jetpack components (Paging, Room, ViewModel, LiveData) and other modern libraries.

## Technologies and Libraries

- **Programming Language**: Kotlin
- **Architecture**: MVVM (Model-View-ViewModel)
- **API Integration**: Retrofit with Kotlin Coroutines
- **Pagination**: Paging 3 library
- **Database**: Room (SQLite)
- **Image Loading**: Glide
- **UI Components**: Material Design Components, RecyclerView, SmoothBottomBar

## Demo
[Demo Apk](https://drive.google.com/file/d/1K-RiqLJBgyio-LpF_OVjDAyLlh2yB4qr/view?usp=sharing)

## Getting Started

To run this project on your local machine:

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/job-listings-app.git
