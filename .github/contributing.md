# UNiDAYS Contributors Guide

:star: Thanks for taking the time to contribute :star:

This document outlines the different ways you can contribute to the UNiDAYS repositories, guidelines on how to do it and some of our standardisations. Everything is open to improvement and all of these are guidelines. If you can think of something we can change or are missing in this doc - you can even open a pull request for making that kind of change.

[Code of Conduct](CODE_OF_CONDUCT.md)

[What should I know before I get started?](#what-should-i-know-before-i-get-started)

[How Can I Contribute?](#how-can-i-contribute)
* [Reporting Bugs](#reporting-bugs)
* [Suggesting Enhancements](#suggesting-enhancements)
* [Labels For Issues](#labels-for-issues)
* [Pull Requests](#pull-requests)
* [Your First Code Contribution](#your-first-code-contribution)

[Style Guides](#style-guides)
* [Documentation Style Guide](#documentation-style-guide)

## How Can I Contribute?

### Reporting Bugs

When issuing a bug report please include as many of details on [the bug report template](./ISSUE_TEMPLATE/bug_report.md) as possible. If you find a Closed issue that seems like it is the same thing that you're experiencing, open a new issue and include a link to the original issue in the body of your new one. Please label the any bug report issues with `bug`.

### Suggesting Enhancements

Enhancements may include completely new features and minor improvements to existing functionality. Following these guidelines helps maintainers and the community understand your suggestion and find related suggestions.

First of all please do a search on the issues already present to see if it's an enhancement that hasn't previously been suggested. If it has, maybe join the discussion on the pre-existing issue. This is to help reduce duplication of issues. If it hasn't been raised previously, please include as many details as you can using [the feature request template](./ISSUE_TEMPLATE/feature_request.md). Label the raised feature request as `enhancement`

### Labels For Issues

| Label | Purpose |
| --- |--- |
| up-for-grabs | An issue that is ready and has enough information to be picked up |
| docs | An issue that only relates to writing docs |
| easy | Difficulty level: any level of experience can pick this issue up |
| medium | Difficulty level: some experience of the domain or language will be needed to pick this issue up |
| hard |Difficulty level: a lot of experience of the domain or language will be needed to pick this issue up |
| insane | Difficulty level: you need to be a total wizard to figure this out |
| enhancement | Making things better but without fixing an issue |
| fix | Fixing a pre-existing problem with the code |

### Pull Requests

* Create branch. If there is an issue, have the branch name related to the linked issue, otherwise give it a descriptive name relating to what the work covers. See some examples below.

| PR-prefix | Purpose | Example |
| --- | --- | --- |
| issuenumber_ | When it relating to a pre-existing issues | issuenumber_583 |
| enhancement | Making things _better_ but without fixing an issue | enhancement_improveperformanceofquery |
| fix | Fixing a pre-existing problem with the code that isn't an issue | fix_acceptnullsforinput |

* Open a Pull Request with the details listed in the [pull request template](../pull_request_template.md). The sections of this template should show in the body of any new pull request automatically.

* Please ensure that any changes you make comply with our [style guides](#style-guides).

* Include screenshots and animated GIFs in your pull request whenever possible.

* Make a comment with the pull request in any issues it relates to.

### Your First Code Contribution?

We will make efforts to label issues with `beginner` if we think they should only require a few lines of code, and a test or two. This is in order to help those who want to contribute but don't necessarily have much experience in doing so.

## Documentation Style Guide

For anything changing the user experience or externally visible implementation, please update the README.md as part of your pull request.

## Licencing

If it's something cool, new and funky that you are contributing, please ensure it's covered with an MIT licence.