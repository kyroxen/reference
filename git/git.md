# GIT ~~(not literally)~~


## Prune
`git gc --prune=now`

## Delete Branch
`git branch -d feature/not-good`

`git push origin --delete feature/not-good`

Or

`git branch -D feature/not-good`

`git push origin --delete feature/not-good`

The distinction? Read this: [https://git-scm.com/docs/git-branch]()

## Rename Branch
1. If you are on the branch you want to rename:
   
   `git branch -m new-name`

2. If you are on a different branch:
   
   `git branch -m old-name new-name`

3. Delete the old-name remote branch and push the new-name local branch.
   
   `git push origin :old-name new-name`

4. Reset the upstream branch for the new-name local branch. First, switch to the branch and then:
   
   `git push origin -u new-name`

## Move uncommitted existing edits into a new branch
Use the following:

`git checkout -b <new-branch>`

This will leave your current branch as is, create and checkout a new branch and keep all your changes. You can then make a commit with:

`git add <files>`

and commit to your new branch with:

`git commit -m "<Brief description of this commit>"`

The changes in the working directory and changes staged in index do not belong to any branch yet. This changes where those changes would end in. You don't reset your original branch, it stays as it is. The last commit on <old-branch> will still be the same. Therefore you checkout -b and then commit.

## Change commit author and email using rebase
If your commit history is with following commit hashes (root) A - B - C - D - E - F (head) and you want to change the 
author of C and D, then you would: 

1. Specify `git rebase -i B` (if you need to edit A, use git rebase -i --root)


2. Change the lines for both C and D from `pick` to `edit`


3. Exit the editor (for vim, this would be pressing Esc and then typing :wq).


4. Once the rebase started, it would first pause at C


5. You would then run `git commit --amend --author="John Doe <john.doe@email.com>"`


6. Then `git rebase --continue`


7. It would pause again at D


8. Then you would run `git commit --amend --author="John Doe <john.doe@email.com>"`


9. `git rebase --continue`


10. The rebase would complete.


11. Use `git push -f` to update your origin with the updated commits.

Also refer: [https://www.git-tower.com/learn/git/faq/change-author-name-email/}()


[https://stackoverflow.com/questions/3042437/how-to-change-the-commit-author-for-one-specific-commit]()

## Delete staged and un-staged changes
To throw away all my staged and un-staged changes, forget everything on my current local branch and make it exactly the same as origin/master.
`git reset --hard origin/master`

## About git reset
git reset do be knowing five "modes": 
1. soft
2. mixed 
3. hard 
4. merge
5. keep
   
I will start with the first three, since these are the modes you'll usually encounter. After that you'll find a nice little a bonus, so stay tuned.

### soft
When using `git reset --soft HEAD~1` you will remove the last commit from the current branch, but the file changes will stay in your working tree. Also the changes will stay on your index, so following with a git commit will create a commit with the exact same changes as the commit you "removed" before.

### mixed
This is the default mode and quite similar to soft. When "removing" a commit with `git reset HEAD~1` you will still keep the changes in your working tree but not on the index; so if you want to "redo" the commit, you will have to add the changes (`git add`) before committing.

### hard
When using `git reset --hard HEAD~1` you will lose all un-commited changes in addition to the changes introduced in the last commit. The changes won't stay in your working tree so doing a git status command will tell you that you don't have any changes in your repository.

Tread carefully with this one. If you accidentally remove un-commited changes which were never tracked by git (speak: committed or at least added to the index), you have no way of getting them back using git.

### Bonus
keep
git reset --keep HEAD~1 is an interesting and useful one. It only resets the files which are different between the current HEAD and the given commit. It aborts the reset if anyone of these files has uncommited changes. It's basically acts as a safer version of hard.

This mode is particularly useful when you have a bunch of changes and want to switch to a different branch without losing these changes - for example when you started to work on the wrong branch.

You can read more about that in the git reset documentation.

### Note
When doing git reset to remove a commit the commit isn't really lost, there just is no reference pointing to it or any of it's children. You can still recover a commit which was "deleted" with git reset by finding it's SHA-1 key, for example with a command such as git reflog.
