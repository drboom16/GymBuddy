// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "GymBuddy",
    platforms: [
        .iOS(.v16)
    ],
    products: [
        .library(
            name: "GymBuddy",
            targets: ["GymBuddy"]),
    ],
    dependencies: [
        // No external dependencies needed for MVP setup
    ],
    targets: [
        .target(
            name: "GymBuddy",
            dependencies: []),
    ]
)